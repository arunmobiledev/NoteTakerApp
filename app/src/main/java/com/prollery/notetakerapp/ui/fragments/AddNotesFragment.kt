package com.prollery.notetakerapp.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.prollery.notetakerapp.R
import com.prollery.notetakerapp.common.extensions.disable
import com.prollery.notetakerapp.common.extensions.enable
import com.prollery.notetakerapp.common.extensions.getDateTime
import com.prollery.notetakerapp.databinding.FragmentAddNotesBinding
import com.prollery.notetakerapp.model.Note
import com.prollery.notetakerapp.ui.viewmodels.NotesViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * AddNotesFragment
 */
class AddNotesFragment : Fragment() {

    private lateinit var binding : FragmentAddNotesBinding
    private val notesViewModel : NotesViewModel by inject{parametersOf(navController)}
    private lateinit var navController: NavController
    private var currentNote : Note? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNotesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        currentNote = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("note", Note::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("note")
        }

        binding.notesViewModel = notesViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        notesViewModel.currentNote = currentNote

        notesViewModel.currentNote?.id?.run {
            notesViewModel.editModeEnabled.postValue(false)
        } ?: run {
            notesViewModel.editModeEnabled.postValue(true)
        }

        notesViewModel.editModeEnabled.observe(viewLifecycleOwner) {
            if(it) enableEditMode() else disableEditMode(notesViewModel.currentNote!!)
        }

    }

    private fun disableEditMode(note : Note) {
        binding.txtTitle.setText(note.title)
        binding.txtDesc.setText(note.description)
        binding.txtTitle.disable()
        binding.txtDesc.disable()
        binding.btnSubmit.text = getString(R.string.edit)
        binding.lblCreatedTime.text = getString(R.string.created_on,
            getDateTime(note.createdTimestampInMillis)
        )
        binding.lblModifiedTime.text = getString(R.string.modified_on,
            getDateTime(note.modifiedTimestampInMillis)
        )
    }

    private fun enableEditMode() {
        binding.txtTitle.enable()
        binding.txtDesc.enable()
        binding.btnSubmit.text = getString(R.string.submit)
        binding.btnSubmit.tag = null
    }

}