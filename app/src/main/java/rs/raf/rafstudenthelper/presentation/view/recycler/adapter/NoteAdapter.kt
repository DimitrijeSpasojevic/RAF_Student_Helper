package rs.raf.rafstudenthelper.presentation.view.recycler.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.data.models.Note
import rs.raf.rafstudenthelper.data.models.NoteWithId
import rs.raf.rafstudenthelper.databinding.LayoutItemCourseBinding
import rs.raf.rafstudenthelper.databinding.LayoutItemNoteBinding
import rs.raf.rafstudenthelper.presentation.view.recycler.consumer.ClickConsumer
import rs.raf.rafstudenthelper.presentation.view.recycler.diff.CourseDiffCallback
import rs.raf.rafstudenthelper.presentation.view.recycler.diff.NoteDiffCallback
import rs.raf.rafstudenthelper.presentation.view.recycler.viewholder.CourseViewHolder
import rs.raf.rafstudenthelper.presentation.view.recycler.viewholder.NoteViewHolder
import java.util.function.Consumer

class NoteAdapter(private val onClickListener: OnClickListener) :
    ListAdapter <NoteWithId, NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemBinding = LayoutItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.btnArchive.setOnClickListener{
            onClickListener.onClick(ClickConsumer(note, ClickConsumer.Click.ARCHIVE))
        }
        holder.btnEdit.setOnClickListener{
            onClickListener.onClick(ClickConsumer(note, ClickConsumer.Click.EDIT))
        }
        holder.btnDelete.setOnClickListener{
            onClickListener.onClick(ClickConsumer(note, ClickConsumer.Click.DELETE))
        }
        holder.bind(note)
    }

    class OnClickListener(val clickListener: (click: ClickConsumer) -> Unit) {
        fun onClick(click: ClickConsumer) = clickListener(click)
    }
}