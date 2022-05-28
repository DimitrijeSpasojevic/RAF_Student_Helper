package rs.raf.rafstudenthelper.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.databinding.LayoutItemCourseBinding
import rs.raf.rafstudenthelper.databinding.LayoutItemNoteBinding
import rs.raf.rafstudenthelper.presentation.view.recycler.diff.CourseDiffCallback
import rs.raf.rafstudenthelper.presentation.view.recycler.diff.NoteDiffCallback
import rs.raf.rafstudenthelper.presentation.view.recycler.viewholder.CourseViewHolder
import rs.raf.rafstudenthelper.presentation.view.recycler.viewholder.NoteViewHolder

class NoteAdapter : ListAdapter <Note, NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemBinding = LayoutItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}