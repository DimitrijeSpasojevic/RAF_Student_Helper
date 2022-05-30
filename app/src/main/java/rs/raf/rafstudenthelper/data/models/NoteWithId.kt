package rs.raf.rafstudenthelper.data.models

import java.sql.Date

data class NoteWithId(
    val id: Long?,
    var title: String,
    var content: String,
    var isArchived: Boolean,
    var date: Date
)