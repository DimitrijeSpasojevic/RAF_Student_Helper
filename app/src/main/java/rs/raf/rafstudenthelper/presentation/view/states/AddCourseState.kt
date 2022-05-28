package rs.raf.rafstudenthelper.presentation.view.states

sealed class AddCourseState {
    object Success: AddCourseState()
    data class Error(val message: String): AddCourseState()
}