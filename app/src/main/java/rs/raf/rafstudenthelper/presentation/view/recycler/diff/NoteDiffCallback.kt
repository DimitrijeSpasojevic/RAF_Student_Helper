package rs.raf.rafstudenthelper.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.Note

class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {


    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.title == newItem.title ||
                oldItem.content == newItem.content
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.title == newItem.title ||
                oldItem.content == newItem.content
    }

}