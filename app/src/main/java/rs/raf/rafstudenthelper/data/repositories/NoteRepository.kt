package rs.raf.rafstudenthelper.data.repositories

import android.graphics.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.data.models.Resource

interface NoteRepository {

    fun getAll(): Observable<List<Note>>
    fun getAllByName(name: String): Observable<List<Note>>
    fun insert(note: Note): Completable

}