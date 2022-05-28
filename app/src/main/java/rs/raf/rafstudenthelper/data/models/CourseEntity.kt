package rs.raf.rafstudenthelper.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val predmet: String,
    val tip: String,
    val nastavnik: String,
    val grupe: String,
    val dan: String,
    val termin: String,
    val ucionica: String
)
