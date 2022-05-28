package rs.raf.rafstudenthelper.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.rafstudenthelper.data.models.CourseResponse

interface CourseService {

    @GET("courses")
    fun getAll(@Query("limit") limit: Int = 1000): Observable<List<CourseResponse>>
}