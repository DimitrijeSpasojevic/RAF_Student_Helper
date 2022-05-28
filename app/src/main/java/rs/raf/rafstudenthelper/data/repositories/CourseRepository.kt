package rs.raf.rafstudenthelper.data.repositories

import android.graphics.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.Resource

interface CourseRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Course>>
    fun getAllByName(name: String): Observable<List<Course>>
    fun insert(course: Course): Completable

}