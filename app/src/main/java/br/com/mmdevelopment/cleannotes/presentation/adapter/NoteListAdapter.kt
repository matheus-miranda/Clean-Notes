package br.com.mmdevelopment.cleannotes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.mmdevelopment.cleannotes.databinding.NoteItemBinding
import br.com.mmdevelopment.cleannotes.domain.model.Note

class NoteListAdapter(private val clickHandler: (Note) -> Unit) :
    RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>() {

    var dataList = emptyList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val inflate = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteListViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.bind(currentItem)
        holder.itemView.setOnClickListener {
            clickHandler(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(noteList: List<Note>) {
        val noteDiffUtil = NoteDiffUtil(dataList, noteList)
        val noteDiffResult = DiffUtil.calculateDiff(noteDiffUtil)
        this.dataList = noteList
        noteDiffResult.dispatchUpdatesTo(this)
    }

    class NoteListViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvDescription.text = note.description

            val dateTime = "${note.date} ${note.time}"
            binding.tvDateTime.text = dateTime
        }
    }
}
