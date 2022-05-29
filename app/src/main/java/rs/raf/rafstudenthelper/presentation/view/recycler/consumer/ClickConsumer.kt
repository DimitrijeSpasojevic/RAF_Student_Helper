package rs.raf.rafstudenthelper.presentation.view.recycler.consumer

import rs.raf.rafstudenthelper.data.models.NoteWithId

class ClickConsumer(
    val note : NoteWithId,
    val click : Click
) {

    enum class Click {
        DELETE, EDIT, ARCHIVE, N
    }
}