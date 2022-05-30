package rs.raf.rafstudenthelper.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.time.LocalDate

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String,
    val content: String,
    val isArchived: Boolean,
    val date: Date = Date.valueOf(LocalDate.now().toString())
)
