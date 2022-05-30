package rs.raf.rafstudenthelper.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.rafstudenthelper.data.converters.Converters
import rs.raf.rafstudenthelper.data.models.CourseEntity
import rs.raf.rafstudenthelper.data.models.NoteEntity

@Database(
    entities = [CourseEntity::class, NoteEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class CourseDataBase : RoomDatabase() {
    abstract fun getCourseDao(): CourseDao
    abstract fun getNoteDao(): NoteDao
}