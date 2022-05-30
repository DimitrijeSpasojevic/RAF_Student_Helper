package rs.raf.rafstudenthelper.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.rafstudenthelper.data.models.NoteEntity
import java.sql.Date

@Dao
abstract class NoteDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: NoteEntity): Completable

    @Update( onConflict = OnConflictStrategy.REPLACE )
    abstract fun update(note: NoteEntity): Completable

    @Delete
    abstract fun deleteNote(note: NoteEntity): Completable

    @Query("DELETE FROM courses")
    abstract fun deleteAll()

//    @Transaction
//    open fun deleteAndInsertAll(entities: List<CourseEntity>) {
//        deleteAll()
//        insertAll(entities).blockingAwait()
//    }

    @Query("SELECT * FROM notes WHERE title LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE title LIKE :name || '%' AND isArchived = 1")
    abstract fun getArchived(name: String): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE title LIKE :name || '%' AND isArchived = 0")
    abstract fun getUnArchived(name: String): Observable<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE date BETWEEN :startDate AND :endDate")
    abstract fun getNotesBetweenDate(startDate: Date, endDate: Date ): Observable<List<NoteEntity>>
}