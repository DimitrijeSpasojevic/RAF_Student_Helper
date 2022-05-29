package rs.raf.rafstudenthelper.presentation.view.states

import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.data.models.NoteWithId

sealed class NotesState {
    object Loading: NotesState()
    object DataFetched: NotesState()
    data class Success(val notes: List<NoteWithId>): NotesState()
    data class Error(val message: String): NotesState()
}