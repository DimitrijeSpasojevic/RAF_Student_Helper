package rs.raf.rafstudenthelper.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.rafstudenthelper.data.models.NoteWithId
import rs.raf.rafstudenthelper.databinding.LayoutItemNoteBinding

class NoteViewHolder(private val itemBinding: LayoutItemNoteBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    var btnArchive = itemBinding.btnArchive
    var btnEdit = itemBinding.btnEdit
    var btnDelete = itemBinding.btnDelete

    fun bind(note: NoteWithId) {
        with(itemBinding){
            titleNote.text = note.title
            contentNote.text = note.content

        }
    }

}