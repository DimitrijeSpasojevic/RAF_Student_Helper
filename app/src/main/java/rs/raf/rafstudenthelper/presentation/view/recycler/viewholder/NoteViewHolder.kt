package rs.raf.rafstudenthelper.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.databinding.LayoutItemCourseBinding
import rs.raf.rafstudenthelper.databinding.LayoutItemNoteBinding

class NoteViewHolder(private val itemBinding: LayoutItemNoteBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(note: Note) {
        with(itemBinding){
            titleNote.text = note.title
            contentNote.text = note.content
        }
    }

}