package rs.raf.rafstudenthelper.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.rafstudenthelper.data.models.CourseEntity

@Database(
    entities = [CourseEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class CourseDataBase : RoomDatabase() {
    abstract fun getCourseDao(): CourseDao
}