package br.com.mmdevelopment.cleannotes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.mmdevelopment.cleannotes.databinding.NoteItemBinding
import br.com.mmdevelopment.cleannotes.domain.model.Note

class NoteListAdapter(private val clickHandler: (Note) -> Unit) :
    ListAdapter<Note, NoteListAdapter.NoteListViewHolder>(DIFF_CONFIG) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteListViewHolder {
        val inflate = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteListViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(position))
        }
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

    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    }
}
