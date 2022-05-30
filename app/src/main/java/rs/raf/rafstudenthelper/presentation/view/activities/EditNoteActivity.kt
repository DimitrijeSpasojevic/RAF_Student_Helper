package rs.raf.rafstudenthelper.presentation.view.activities

import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.rafstudenthelper.data.models.NoteEntity
import rs.raf.rafstudenthelper.databinding.FragmentEditNoteBinding
import rs.raf.rafstudenthelper.presentation.contract.NoteContract
import rs.raf.rafstudenthelper.presentation.viewmodel.NoteViewModel

class EditNoteActivity : AppCompatActivity() {

    private lateinit var binding: FragmentEditNoteBinding
    private val noteViewModel by viewModel<NoteViewModel>()
    private var id:Long = 0
    private var title:String = ""
    private var content:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            val extras = intent.extras
            if (extras != null) {
                id = extras.getLong("id")
                content = extras.getString("content").toString()
                title = extras.getString("title").toString()
            }
        }
        init()
        intiui()
    }

    private fun intiui() {
        binding.noteTitle.setText(title)
        binding.noteContent.setText(content)
    }
    private fun init(){

        with(binding){
            btnEdit.setOnClickListener {
                noteViewModel.updateNote(NoteEntity(
                    id = id ,
                    title = noteTitle.text.toString(), content = noteContent.text.toString(), isArchived = false))
                finish()
            }

            btnCancel.setOnClickListener {
                finish()
            }
        }
    }
}