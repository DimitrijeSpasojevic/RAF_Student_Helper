package rs.raf.rafstudenthelper.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers.Main
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.rafstudenthelper.R
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.data.models.NoteEntity
import rs.raf.rafstudenthelper.databinding.FragmentNotesBinding
import rs.raf.rafstudenthelper.presentation.contract.NoteContract
import rs.raf.rafstudenthelper.presentation.view.activities.EditNoteActivity
import rs.raf.rafstudenthelper.presentation.view.activities.NewNoteActivity
import rs.raf.rafstudenthelper.presentation.view.recycler.adapter.NoteAdapter
import rs.raf.rafstudenthelper.presentation.view.recycler.consumer.ClickConsumer
import rs.raf.rafstudenthelper.presentation.view.states.NotesState
import rs.raf.rafstudenthelper.presentation.view.states.UpdateNoteState
import rs.raf.rafstudenthelper.presentation.viewmodel.NoteViewModel
import java.util.*

class InputFragment : Fragment(R.layout.fragment_notes) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val noteViewModel: NoteContract.ViewModel by sharedViewModel<NoteViewModel>()

    private var _binding: FragmentNotesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: NoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        binding.btnAddNewNote.setOnClickListener{
            val intent = Intent (activity, NewNoteActivity::class.java)
            activity?.startActivity(intent)
        }

//        binding.addBtn.setOnClickListener {
//            val input = binding.inputEt.text.toString()
//            if (input.isBlank()) {
//                Toast.makeText(context, "Input cannot be empty!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            binding.inputEt.text.clear()
//
//            val temp: UUID = UUID.randomUUID()
//
//            val movieToAdd = Movie(
//                id = Long.toHexString(temp.mostSignificantBits)
//                        + Long.toHexString(temp.leastSignificantBits),
//                title = input
//            )
//            mainViewModel.addMovie(movieToAdd)
//        }
    }

    private fun initRecycler() {
        binding.listRvNote.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(NoteAdapter.OnClickListener {
            when (it.click) {
                ClickConsumer.Click.ARCHIVE -> {
                    it.note.isArchived = !it.note.isArchived
                    noteViewModel.updateNote(NoteEntity(it.note.id,it.note.title,it.note.content,it.note.isArchived))
                }
                ClickConsumer.Click.EDIT -> {
                    val intent = Intent (activity, EditNoteActivity::class.java)
                    intent.putExtra("id",it.note.id)
                    intent.putExtra("content",it.note.content)
                    intent.putExtra("title",it.note.title)
                    activity?.startActivity(intent)
                }
                ClickConsumer.Click.DELETE -> {
                    noteViewModel.deleteNote(NoteEntity(it.note.id,it.note.title,it.note.content,it.note.isArchived))
                }
            }
        })
        binding.listRvNote.adapter = adapter
    }

    private fun initObservers() {
        noteViewModel.notesState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })

//        noteViewModel.updateNoteState.observe(viewLifecycleOwner, Observer {
//            when(it){
//                is UpdateNoteState.Success ->{
//                    Log.e("SUCCCCC","afafaf")
//                }
//                is UpdateNoteState.Error -> {
//                    Log.e("ERRRR","afafaf")
//                }
//            }
//        })

        noteViewModel.getAllNotes()
    }

    private fun renderState(state: NotesState) {
        when(state) {
            is NotesState.Success -> {
                Toast.makeText(context, "Notes Succesed", Toast.LENGTH_SHORT)
                    .show()
                adapter.submitList(state.notes)
            }
            is NotesState.Error -> Toast.makeText(context, "Error happened", Toast.LENGTH_SHORT)
                .show()
            is NotesState.DataFetched-> Toast.makeText(context, "DataFetched happened", Toast.LENGTH_SHORT)
                .show()
            is NotesState.Loading -> Toast.makeText(context, "Loading happened", Toast.LENGTH_SHORT)
                .show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}