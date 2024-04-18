package com.prollery.notetakerapp.ui.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.prollery.notetakerapp.common.extensions.getDefaultTime
import com.prollery.notetakerapp.databinding.FragmentAddNotesBinding
import com.prollery.notetakerapp.model.Note
import com.prollery.notetakerapp.ui.viewmodels.NotesViewModel
import org.koin.android.ext.android.inject
import java.util.Date

/**
 * AddNotesFragment
 */
class AddNotesFragment : Fragment() {

    private lateinit var binding : FragmentAddNotesBinding
    private val notesViewModel : NotesViewModel by inject()
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

        currentNote?.run {
            binding.txtTitle.setText(title)
            binding.txtDesc.setText(description)
        }

        binding.btnSubmit.setOnClickListener {
            currentNote?.run {
                title = binding.txtTitle.text.toString().trim()
                description = binding.txtDesc.text.toString().trim()
                modifiedTimestamp = Date().getDefaultTime()
                modifiedTimestampInMillis = System.currentTimeMillis()
                notesViewModel.updateNote(this)
            } ?: run {
                val note = Note(title = binding.txtTitle.text.toString().trim()
                    , description = binding.txtDesc.text.toString().trim()
                    , createdTimestamp = Date().getDefaultTime()
                    , createdTimestampInMillis = System.currentTimeMillis()
                    , modifiedTimestamp = Date().getDefaultTime()
                    , modifiedTimestampInMillis = System.currentTimeMillis()
                )
                notesViewModel.createNote(note)
            }
            navController.popBackStack()
        }
    }

}