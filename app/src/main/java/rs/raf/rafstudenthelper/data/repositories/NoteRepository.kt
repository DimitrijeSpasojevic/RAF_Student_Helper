package rs.raf.rafstudenthelper.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.rafstudenthelper.data.models.*

interface NoteRepository {

    fun getAll(): Observable<List<NoteWithId>>
    fun getAllByName(name: String): Observable<List<NoteWithId>>
    fun insert(note: Note): Completable
    fun deleteNote(note: NoteEntity): Completable
    fun updateNote(note: NoteEntity): Completable

}