package rs.raf.rafstudenthelper.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.data.models.NoteWithId

class NoteDiffCallback : DiffUtil.ItemCallback<NoteWithId>() {


    override fun areItemsTheSame(oldItem: NoteWithId, newItem: NoteWithId): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteWithId, newItem: NoteWithId): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.content == newItem.content
    }

}