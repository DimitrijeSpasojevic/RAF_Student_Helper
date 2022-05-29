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

    @Query("SELECT grupe FROM courses")
    abstract fun getAllGroups(): Observable<List<String>>

    @Query("SELECT dan FROM courses")
    abstract fun getAllDays(): Observable<List<String>>

    @Query("DELETE FROM courses")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<CourseEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM courses WHERE predmet LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<CourseEntity>>

    @Query("SELECT * FROM courses" +
            " WHERE " + "(" + " predmet LIKE :name || '%' OR :name IS NULL" + ")" +
            "AND " + "(" + " grupe LIKE :group || '%' OR :group IS NULL" + ")" +
            "AND " + "(" + " dan LIKE :day || '%' OR :day IS NULL" + ")" +
            "AND " + "(" + " nastavnik LIKE :professor || '%' OR :professor IS NULL" + ")")
    abstract fun getAllFiltered(name: String, group: String, day: String, professor: String): Observable<List<CourseEntity>>

}