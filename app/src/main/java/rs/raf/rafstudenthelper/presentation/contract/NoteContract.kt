package rs.raf.rafstudenthelper.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.data.models.NoteEntity
import rs.raf.rafstudenthelper.data.models.NoteWithId
import rs.raf.rafstudenthelper.presentation.view.states.AddNoteState
import rs.raf.rafstudenthelper.presentation.view.states.NotesState
import rs.raf.rafstudenthelper.presentation.view.states.NotesStatisticsState
import rs.raf.rafstudenthelper.presentation.view.states.UpdateNoteState
import java.sql.Date

interface NoteContract {

    interface ViewModel{
        val notesState: LiveData<NotesState>
        val addDone: LiveData<AddNoteState>
        val updateNoteState: LiveData<UpdateNoteState>
        val notesStatisticsState: LiveData<NotesStatisticsState>

        fun addNote(note: Note)
        fun deleteNote(note: NoteEntity)
        fun updateNote(note: NoteEntity)
        fun getArchive(name: String)
        fun getUnArchive(name: String)
        fun getNotesBetweenDate(startDate: Date, endDate: Date)
    }
}