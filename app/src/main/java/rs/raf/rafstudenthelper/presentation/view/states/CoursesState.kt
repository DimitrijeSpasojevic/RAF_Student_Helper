package rs.raf.rafstudenthelper.presentation.view.states

import rs.raf.rafstudenthelper.data.models.Course

sealed class CoursesState {
    object Loading: CoursesState()
    object DataFetched: CoursesState()
    data class Success(val courses: List<Course>): CoursesState()
    data class Error(val message: String): CoursesState()
}