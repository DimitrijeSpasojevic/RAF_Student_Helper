package rs.raf.rafstudenthelper.presentation.view.fragments

import android.app.ProgressDialog.show
import android.os.Bundle
import android.util.Log.e
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.rafstudenthelper.R
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.databinding.FragmentNotesBinding
import rs.raf.rafstudenthelper.presentation.contract.MainContract
import rs.raf.rafstudenthelper.presentation.contract.NoteContract
import rs.raf.rafstudenthelper.presentation.view.recycler.adapter.MovieAdapter
import rs.raf.rafstudenthelper.presentation.view.recycler.adapter.NoteAdapter
import rs.raf.rafstudenthelper.presentation.view.states.AddCourseState
import rs.raf.rafstudenthelper.presentation.view.states.AddNoteState
import rs.raf.rafstudenthelper.presentation.view.states.NotesState
import rs.raf.rafstudenthelper.presentation.viewmodel.MainViewModel
import rs.raf.rafstudenthelper.presentation.viewmodel.NoteViewModel
import timber.log.Timber
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
            noteViewModel.addNote(Note("Proba-Nalsov prvog nota", "Dugacak tekst prvog nota"))
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
        adapter = NoteAdapter()
        binding.listRvNote.adapter = adapter
    }

    private fun initObservers() {
        noteViewModel.notesState.observe(viewLifecycleOwner, Observer {
            renderState(it)
        })

        noteViewModel.getAllNotes()
    }

    private fun renderState(state: NotesState) {
        when(state) {
            is NotesState.Success -> {
                Toast.makeText(context, "Notes Succesed", Toast.LENGTH_SHORT)
                    .show()
//                showLoadingState(false)
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