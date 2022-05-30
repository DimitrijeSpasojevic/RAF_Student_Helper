package rs.raf.rafstudenthelper.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.rafstudenthelper.data.models.Course

class CourseDiffCallback : DiffUtil.ItemCallback<Course>() {

    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.predmet == newItem.predmet &&
                oldItem.ucionica == newItem.ucionica &&
                oldItem.nastavnik == newItem.nastavnik &&
                oldItem.termin == newItem.termin &&
                oldItem.grupe == newItem.grupe &&
                oldItem.dan == newItem.dan &&
                oldItem.tip == newItem.tip
    }

    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
        return oldItem.predmet == newItem.predmet &&
                oldItem.ucionica == newItem.ucionica &&
                oldItem.nastavnik == newItem.nastavnik &&
                oldItem.termin == newItem.termin &&
                oldItem.grupe == newItem.grupe &&
                oldItem.dan == newItem.dan &&
                oldItem.tip == newItem.tip
    }

}