package br.com.mmdevelopment.cleannotes.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mmdevelopment.cleannotes.databinding.FragmentHomeBinding
import br.com.mmdevelopment.cleannotes.domain.model.Note
import br.com.mmdevelopment.cleannotes.presentation.adapter.NoteListAdapter
import br.com.mmdevelopment.cleannotes.presentation.home.HomeContract
import br.com.mmdevelopment.cleannotes.presentation.home.HomeContract.Presenter
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(), HomeContract.View {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val presenter: Presenter? by inject()
    private val adapter by lazy { NoteListAdapter { editNoteNavigation(it) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter!!.setView(this)
        bindAdapter()
        bindListeners()
        // bindSearchView() TODO get correct adapter position from new list when clicked, else crashes
        presenter!!.getAllNotes()
    }

    private fun bindAdapter() {
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter
        showEmptyList(false)
    }

    private fun bindListeners() {
        binding.fabNew.setOnClickListener {
            addNewNote()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter!!.detachView()
        _binding = null
    }

    override fun bindSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    presenter!!.onSearch(it)
                }
                return true
            }
        })
    }

    override fun showNotesOnRecycleView(list: List<Note>) {
        adapter.submitList(list)
    }

    override fun editNoteNavigation(note: Note) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNoteFragment(note))
    }

    override fun showEmptyList(shouldDisplay: Boolean) {
        if (shouldDisplay) {
            binding.apply {
                emptyInclude.emptyState.visibility = View.VISIBLE
                rvNotes.visibility = View.GONE
            }
        } else {
            binding.apply {
                emptyInclude.emptyState.visibility = View.GONE
                rvNotes.visibility = View.VISIBLE
            }
        }
    }

    override fun addNewNote() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNoteFragment(null))
    }

}