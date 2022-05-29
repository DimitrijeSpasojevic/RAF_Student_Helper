package rs.raf.rafstudenthelper.presentation.view.states

sealed class UpdateNoteState {
    object Success: UpdateNoteState()
    data class Error(val message: String): UpdateNoteState()
}