package br.com.mmdevelopment.cleannotes.presentation.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.mmdevelopment.cleannotes.R
import br.com.mmdevelopment.cleannotes.databinding.FragmentHomeBinding
import br.com.mmdevelopment.cleannotes.domain.model.Note
import br.com.mmdevelopment.cleannotes.presentation.adapter.NoteListAdapter
import br.com.mmdevelopment.cleannotes.presentation.home.HomeContract
import br.com.mmdevelopment.cleannotes.presentation.home.HomeContract.Presenter
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class HomeFragment : Fragment(), HomeContract.View {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val presenter: Presenter? by inject()

    private val adapter by lazy { NoteListAdapter { editNoteNavigation(it) } }
    private lateinit var sharedPreferences: SharedPreferences
    private var isLinearLayout = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        sharedPreferences.edit().putBoolean(IS_LINEAR_LAYOUT_KEY, isLinearLayout).apply()

    }

    override fun onResume() {
        super.onResume()
        getSharedPreferences()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter!!.setView(this)
        getSharedPreferences()
        bindAdapter()
        swipeToDelete()
        setRvManager()
        bindListeners()
        presenter!!.getAllNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter!!.detachView()
        _binding = null
    }

    private fun getSharedPreferences() {
        sharedPreferences =
            requireContext().getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
        isLinearLayout = sharedPreferences.getBoolean(IS_LINEAR_LAYOUT_KEY, true)
    }

    private fun bindAdapter() {
        binding.rvNotes.adapter = adapter
        showEmptyList(false)
    }

    override fun bindListeners() {
        binding.fabNew.setOnClickListener {
            addNewNote()
        }
    }

    override fun bindSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val layoutButton = menu.findItem(R.id.switch_layout)

        val searchViewItem = menu.findItem(R.id.menu_search)
        val searchView = searchViewItem.actionView as SearchView
        bindSearchView(searchView)

        setIcon(layoutButton)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.switch_layout -> {
                isLinearLayout = !isLinearLayout
                sharedPreferences.edit().putBoolean(IS_LINEAR_LAYOUT_KEY, isLinearLayout).apply()
                setRvManager()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setIcon(menuItem: MenuItem) {
        menuItem.icon = if (isLinearLayout) {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_linear_layout)
        } else {
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_grid_layout)
        }
    }

    override fun setRvManager() {
        if (isLinearLayout) {
            binding.rvNotes.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        } else {
            binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun showNotesOnRecycleView(list: List<Note>) {
        adapter.submitList(list)
    }

    override fun editNoteNavigation(note: Note) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNoteFragment(note.id))
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
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddNoteFragment(0))
    }

    override fun swipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val note = adapter.currentList[position]
                presenter!!.delete(note)

                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.deleted),
                    Snackbar.LENGTH_LONG
                ).setAnchorView(binding.fabNew)
                    .apply {
                        setAction(resources.getString(R.string.undo)) {
                            presenter!!.insertNote(note)
                        }
                        show()
                    }
            }

        }).attachToRecyclerView(binding.rvNotes)
    }

    companion object {
        private const val IS_LINEAR_LAYOUT_KEY = "is_linear"
    }
}