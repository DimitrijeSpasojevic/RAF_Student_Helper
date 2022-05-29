package rs.raf.rafstudenthelper.data.models

data class NoteWithId(
    val id: Long?,
    var title: String,
    var content: String,
    var isArchived: Boolean
)