package br.com.mmdevelopment.cleannotes.ui

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.mmdevelopment.cleannotes.R
import br.com.mmdevelopment.cleannotes.adapter.NoteListAdapter
import br.com.mmdevelopment.cleannotes.databinding.ActivityMainBinding
import br.com.mmdevelopment.cleannotes.datasource.NoteDataSource
import br.com.mmdevelopment.cleannotes.model.Note
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvNote: RecyclerView
    private lateinit var toolbar: MaterialToolbar
    private var isLinearLayoutManager = true
    private val adapter by lazy { NoteListAdapter { clickedListItem(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animationTransitions() // Call animations before inflating the layout

        // Inflate the layout with ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inflate toolbar with options menu
        toolbar = binding.tbMain
        toolbar.title = ""
        setSupportActionBar(toolbar)

        // Initialize the RecyclerView and choose the layout
        rvNote = binding.rvNotes
        rvNote.adapter = adapter
        swipeToDelete()
        chooseLayout()

        insertListeners() // Handle click listeners
    }

    override fun onResume() {
        super.onResume()
        updateList() // Necessary to redraw list for when the user switches to dark mode
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val layoutButton = menu?.findItem(R.id.switch_layout)
        setIcon(layoutButton)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.switch_layout -> {
                // Sets isGridLayoutManager to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooseLayout()
                setIcon(item)

                return true
            }
            //  Otherwise, do nothing and use the core event handling
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE_OK && resultCode == Activity.RESULT_OK) {
            //rvNote.adapter = adapter
            updateList()
        }
    }

    /**
     * Update the list adapter
     */
    private fun updateList() {
        adapter.submitList(NoteDataSource.getList())
    }

    /**
     * Switches the LayoutManager
     */
    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            rvNote.layoutManager = LinearLayoutManager(this)
        } else {
            rvNote.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    /**
     * Set menu icon for layout and theme
     */
    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null) return

        menuItem.icon = if (isLinearLayoutManager)
            ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
    }

    /**
     * Fab clickListener
     */
    private fun insertListeners() {
        binding.fabNew.setOnClickListener {
            startActivityForResult(
                Intent(this, AddNoteActivity::class.java),
                RESULT_CODE_OK,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            )
        }
    }

    /**
     * Called whenever a note is clicked by the user
     */
    private fun clickedListItem(note: Note) {
        val intent = Intent(this, AddNoteActivity::class.java).also {
            it.putExtra(AddNoteActivity.TASK_ID, note.id)
        }
        startActivityForResult(intent, RESULT_CODE_OK)
    }

    /**
     * Handles the swipe on recycler view to delete item
     */
    private fun swipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = adapter.currentList[position]
                NoteDataSource.deleteNote(item)
                updateList()
            }

        }).attachToRecyclerView(rvNote)
    }

    /**
     * Creates transition animations
     */
    private fun animationTransitions() {
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            // set the transition to be shown when the user enters this activity
            enterTransition = Explode()
            enterTransition.duration = 600
        }
    }

    companion object {
        private const val RESULT_CODE_OK = 1000
    }
}