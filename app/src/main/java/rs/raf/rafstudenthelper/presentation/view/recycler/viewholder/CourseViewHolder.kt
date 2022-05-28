package rs.raf.rafstudenthelper.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.rafstudenthelper.data.models.Course
import rs.raf.rafstudenthelper.databinding.LayoutItemCourseBinding

class CourseViewHolder(private val itemBinding: LayoutItemCourseBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(course: Course) {
        with(itemBinding){
            titleTv.text = course.predmet
            tipPredmeta.text = course.tip
            profesor.text = course.nastavnik
            ucionica.text = course.ucionica
            grupa.text = course.grupe
            dan.text = course.dan
            vreme.text = course.termin
        }
    }

}