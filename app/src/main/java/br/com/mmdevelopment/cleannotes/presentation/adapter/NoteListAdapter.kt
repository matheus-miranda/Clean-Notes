package br.com.mmdevelopment.cleannotes.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.mmdevelopment.cleannotes.databinding.NoteItemBinding
import br.com.mmdevelopment.cleannotes.data.local.entity.NoteEntity

class NoteListAdapter(private val clickHandler: (NoteEntity) -> Unit) :
    ListAdapter<NoteEntity, NoteListAdapter.NoteListViewHolder>(DIFF_CONFIG) {

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

    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<NoteEntity>() {
            override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    class NoteListViewHolder(private val binding: NoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(note: NoteEntity) {
            binding.tvTitle.text = note.title
            binding.tvDescription.text = note.description

            val dateTime = "${note.date} ${note.time}"
            binding.tvDateTime.text = dateTime
        }
    }
}