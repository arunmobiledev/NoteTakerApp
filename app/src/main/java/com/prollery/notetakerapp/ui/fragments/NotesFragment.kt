package com.prollery.notetakerapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.prollery.notetakerapp.R
import com.prollery.notetakerapp.common.extensions.navigateSafely
import com.prollery.notetakerapp.common.interfaces.OnItemClickListener
import com.prollery.notetakerapp.databinding.FragmentNotesBinding
import com.prollery.notetakerapp.repo.NotesRepo
import com.prollery.notetakerapp.ui.adapters.NotesAdapter
import com.prollery.notetakerapp.ui.viewmodels.NotesViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Notes Fragment
 */
class NotesFragment : Fragment() , OnItemClickListener {

    private lateinit var binding : FragmentNotesBinding
    private lateinit var adaptor : NotesAdapter
    private val notesViewModel : NotesViewModel by inject()
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding.rvNotes.layoutManager = GridLayoutManager(requireContext(), 2)

        notesViewModel.fetchAllNotes()

        notesViewModel.lstNotes.observe(viewLifecycleOwner) {
            adaptor = NotesAdapter(it, notesViewModel, this)
            binding.rvNotes.adapter = adaptor
        }

    }

    override fun onItemClick(itemId: Long, viewType: String) {
        when {
            viewType == "btnDelete" -> {
                notesViewModel.deleteNote(itemId)
            }
            viewType == "btnUpdate" -> {
                val note = notesViewModel.lstNotes.value?.findLast { it.id == itemId }
                val b = Bundle()
                b.putParcelable("note", note)
                navController.navigateSafely(R.id.action_notesFragment_to_addNotesFragment, b)
            }
        }
    }

}