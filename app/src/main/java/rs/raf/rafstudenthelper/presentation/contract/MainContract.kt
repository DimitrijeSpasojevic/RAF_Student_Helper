package rs.raf.rafstudenthelper.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.presentation.view.states.AddCourseState
import rs.raf.rafstudenthelper.presentation.view.states.CoursesState

interface MainContract {

    interface ViewModel {

        val coursesState: LiveData<CoursesState>
        val addDone: LiveData<AddCourseState>

        fun fetchAllCourses()
        fun getAllCourses()
        fun getCoursesByName(name: String)
        fun addCourse(course: Course)
    }

}