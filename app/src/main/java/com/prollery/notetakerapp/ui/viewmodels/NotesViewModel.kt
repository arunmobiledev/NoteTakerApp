package com.prollery.notetakerapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prollery.notetakerapp.model.Note
import com.prollery.notetakerapp.repo.NotesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(private val notesRepo: NotesRepo) : ViewModel() {

    var lstNotes: LiveData<List<Note>> = notesRepo.allNotes

    fun createNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepo.createNote(note)
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepo.deleteNote(id)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepo.updateNote(note)
        }
    }

    fun fetchAllNotes() {
        notesRepo.fetchAllNotes()
    }

}