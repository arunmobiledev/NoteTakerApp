package com.prollery.notetakerapp.ui.viewmodels

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import com.prollery.notetakerapp.R
import com.prollery.notetakerapp.common.extensions.getDefaultTime
import com.prollery.notetakerapp.common.extensions.navigateSafely
import com.prollery.notetakerapp.model.Note
import com.prollery.notetakerapp.repo.NotesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import com.prollery.notetakerapp.common.interfaces.OnItemClickListener

class NotesViewModel(private val notesRepo: NotesRepo, private val navController: NavController) : ViewModel() {

    var title = MutableLiveData<String>()
    var description = MutableLiveData<String>()

    var lstNotes: LiveData<List<Note>> = notesRepo.allNotes

    val editModeEnabled = MutableLiveData<Boolean>()

    var currentNote : Note? = null
    var notesItemClickListener : NotesItemClickListener

    init {
        notesItemClickListener = NotesItemClickListener()
    }

    private fun createNote(onComplete : (Note) -> Unit ) {
        viewModelScope.launch(Dispatchers.IO) {
            val newNote = Note(title = (this@NotesViewModel.title.value ?: "").trim()
                , description = (this@NotesViewModel.description.value ?: "").trim()
                , createdTimestamp = Date().getDefaultTime()
                , createdTimestampInMillis = System.currentTimeMillis()
                , modifiedTimestamp = Date().getDefaultTime()
                , modifiedTimestampInMillis = System.currentTimeMillis()
            )
            newNote.id =  notesRepo.createNote(newNote)
            onComplete(newNote)
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepo.deleteNote(id)
        }
    }

    private fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            note.title = this@NotesViewModel.title.value ?: ""
            note.description = this@NotesViewModel.description.value ?: ""
            note.modifiedTimestamp = Date().getDefaultTime()
            note.modifiedTimestampInMillis = System.currentTimeMillis()
            notesRepo.updateNote(note)
        }
    }

    fun callNoteDatabaseExportCheckpoint() {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepo.callNoteDatabaseExportCheckpoint()
        }
    }

    fun onSubmit() {
        currentNote?.id?.run {
            editModeEnabled.value?.let {
                if(it) {
                    updateNote(currentNote!!)
                    editModeEnabled.postValue(false)
                } else {
                    editModeEnabled.postValue(true)
                }
            }
        } ?: run {
            createNote {
                currentNote = it
                editModeEnabled.postValue(false)
            }
        }
    }

    inner class NotesItemClickListener : OnItemClickListener {
        override fun onItemClick(itemId: Long, viewType: String) {
            when (viewType) {
                "btnDelete" -> {
                    deleteNote(itemId)
                }
                "btnUpdate" -> {
                    val note = lstNotes.value?.findLast { it.id == itemId }
                    val b = Bundle()
                    b.putParcelable("note", note)
                    navController.navigateSafely(R.id.action_notesFragment_to_addNotesFragment, b)
                }
            }
        }

    }

}