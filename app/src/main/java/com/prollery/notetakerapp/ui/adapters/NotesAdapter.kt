package com.prollery.notetakerapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prollery.notetakerapp.common.interfaces.OnItemClickListener
import com.prollery.notetakerapp.databinding.ItemNotesBinding
import com.prollery.notetakerapp.model.Note
import com.prollery.notetakerapp.ui.viewmodels.NotesViewModel

class NotesAdapter(_lstNotes : List<Note>, val notesViewModel: NotesViewModel, val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var lstNotes: List<Note> = _lstNotes
    inner class ViewHolder(private val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.lblTitle.text = note.title
            binding.lblDescription.text = note.description
            binding.lblCreatedTime.text = note.createdTimestamp
            binding.lblModifiedTime.text = note.modifiedTimestamp
            binding.lnrRoot.tag = note
            binding.btnDelete.tag = note
            binding.lnrRoot.setOnClickListener {
                val note1 = it.tag as Note
                onItemClickListener.onItemClick(note1.id, "btnUpdate")
            }

            binding.btnDelete.setOnClickListener {
                val note1 = it.tag as Note
                onItemClickListener.onItemClick(note1.id, "btnDelete")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lstNotes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = lstNotes[position]
        holder.bind(note)
    }
}