package rs.raf.rafstudenthelper.presentation.view.states

sealed class AddNoteState {
    object Success: AddNoteState()
    data class Error(val message: String): AddNoteState()
}