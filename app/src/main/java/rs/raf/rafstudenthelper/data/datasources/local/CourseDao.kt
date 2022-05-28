package rs.raf.rafstudenthelper.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.rafstudenthelper.data.models.CourseEntity

@Dao
abstract class CourseDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: CourseEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<CourseEntity>): Completable

    @Query("SELECT * FROM courses")
    abstract fun getAll(): Observable<List<CourseEntity>>

    @Query("DELETE FROM courses")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<CourseEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM courses WHERE predmet LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<CourseEntity>>

}