package rs.raf.rafstudenthelper.presentation.view.states

import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.data.models.NoteWithId

sealed class NotesStatisticsState {
    data class Success(val notes: List<NoteWithId>): NotesStatisticsState()
    data class Error(val message: String): NotesStatisticsState()
}