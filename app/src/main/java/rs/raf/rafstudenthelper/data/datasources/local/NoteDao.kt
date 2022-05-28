package rs.raf.rafstudenthelper.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.rafstudenthelper.data.models.CourseEntity
import rs.raf.rafstudenthelper.data.models.NoteEntity

@Dao
abstract class NoteDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: NoteEntity): Completable

//    @Insert( onConflict = OnConflictStrategy.REPLACE )
//    abstract fun insertAll(entities: List<CourseEntity>): Completable

    @Query("SELECT * FROM notes")
    abstract fun getAll(): Observable<List<NoteEntity>>

    @Query("DELETE FROM courses")
    abstract fun deleteAll()

//    @Transaction
//    open fun deleteAndInsertAll(entities: List<CourseEntity>) {
//        deleteAll()
//        insertAll(entities).blockingAwait()
//    }

    @Query("SELECT * FROM notes WHERE title LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<NoteEntity>>

}