package rs.raf.rafstudenthelper.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.databinding.FragmentNewNoteBinding
import rs.raf.rafstudenthelper.presentation.contract.NoteContract
import rs.raf.rafstudenthelper.presentation.view.fragments.InputFragment
import rs.raf.rafstudenthelper.presentation.viewmodel.NoteViewModel

class NewNoteActivity : AppCompatActivity() {

    private lateinit var binding: FragmentNewNoteBinding
    private val noteViewModel by viewModel<NoteViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentNewNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){

        with(binding){
            btnIzmeni.setOnClickListener {
                noteViewModel.addNote(Note(title = noteTitle.text.toString(), content = noteContent.text.toString(), isArchived = false))
                finish()
            }

            btnOdustani.setOnClickListener {
                finish()
            }
        }
    }
}