package rs.raf.rafstudenthelper.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.rafstudenthelper.data.models.*
import java.sql.Date

interface NoteRepository {

    fun insert(note: Note): Completable
    fun deleteNote(note: NoteEntity): Completable
    fun updateNote(note: NoteEntity): Completable
    fun getArchived(name: String): Observable<List<NoteWithId>>
    fun getUnArchived(name: String): Observable<List<NoteWithId>>
    fun getUnNotesBtweenDates(startDate: Date, endDate: Date): Observable<List<NoteWithId>>
}