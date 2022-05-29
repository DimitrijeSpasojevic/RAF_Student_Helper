package rs.raf.rafstudenthelper.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.data.models.NoteEntity
import rs.raf.rafstudenthelper.data.models.NoteWithId
import rs.raf.rafstudenthelper.presentation.view.states.AddNoteState
import rs.raf.rafstudenthelper.presentation.view.states.NotesState
import rs.raf.rafstudenthelper.presentation.view.states.UpdateNoteState

interface NoteContract {

    interface ViewModel{
        val notesState: LiveData<NotesState>
        val addDone: LiveData<AddNoteState>
        val updateNoteState: LiveData<UpdateNoteState>

        fun getAllNotes()
        fun getNotesByName(name: String)
        fun addNote(note: Note)
        fun deleteNote(note: NoteEntity)
        fun updateNote(note: NoteEntity)
    }
}