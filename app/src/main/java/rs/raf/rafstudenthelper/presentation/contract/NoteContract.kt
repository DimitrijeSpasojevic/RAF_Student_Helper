package rs.raf.rafstudenthelper.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.presentation.view.states.AddCourseState
import rs.raf.rafstudenthelper.presentation.view.states.AddNoteState
import rs.raf.rafstudenthelper.presentation.view.states.CoursesState
import rs.raf.rafstudenthelper.presentation.view.states.NotesState

interface NoteContract {

    interface ViewModel{
        val notesState: LiveData<NotesState>
        val addDone: LiveData<AddNoteState>

        fun getAllNotes()
        fun getNotesByName(name: String)
        fun addNote(note: Note)
    }
}