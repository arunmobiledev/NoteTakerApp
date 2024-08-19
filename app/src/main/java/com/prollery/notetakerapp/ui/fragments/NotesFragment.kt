package com.prollery.notetakerapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.prollery.notetakerapp.R
import com.prollery.notetakerapp.common.extensions.navigateSafely
import com.prollery.notetakerapp.databinding.FragmentNotesBinding
import com.prollery.notetakerapp.ui.adapters.NotesAdapter
import com.prollery.notetakerapp.ui.viewmodels.NotesViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

/**
 * Notes Fragment
 */
class NotesFragment : Fragment()  {

    private lateinit var binding : FragmentNotesBinding
    private lateinit var adaptor : NotesAdapter
    private val notesViewModel : NotesViewModel by inject{ parametersOf(navController) }
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
        binding.rvNotes.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)

        notesViewModel.lstNotes.observe(viewLifecycleOwner) { lstNotes ->
            if(!::adaptor.isInitialized) {
                adaptor = NotesAdapter(notesViewModel)
            }
            adaptor.lstNotes = lstNotes
            binding.rvNotes.adapter = adaptor
        }

        binding.fab.setOnClickListener {
            navController.navigateSafely(R.id.action_notesFragment_to_addNotesFragment)
        }

    }

}