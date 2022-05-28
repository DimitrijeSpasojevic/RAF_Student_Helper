package rs.raf.rafstudenthelper.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.databinding.LayoutItemCourseBinding
import rs.raf.rafstudenthelper.presentation.view.recycler.diff.CourseDiffCallback
import rs.raf.rafstudenthelper.presentation.view.recycler.viewholder.CourseViewHolder

class MovieAdapter : ListAdapter<Course, CourseViewHolder>(CourseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemBinding = LayoutItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}