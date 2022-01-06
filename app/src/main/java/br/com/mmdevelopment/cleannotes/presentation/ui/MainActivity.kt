package br.com.mmdevelopment.cleannotes.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.mmdevelopment.cleannotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private lateinit var rvNote: RecyclerView
//    private lateinit var toolbar: MaterialToolbar
//    private lateinit var sharedPreferences: SharedPreferences
//    private val viewModel: SharedViewModel by viewModel()
    //private val adapter by lazy { NoteListAdapter { clickedListItem(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //animationTransitions() // Call animations before inflating the layout
        // Inflate the layout with ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //sharedPreferences = this.getSharedPreferences("layout", MODE_PRIVATE)
       // getSharedPref()

        //setToolbar() // Inflate toolbar with options menu

        // Initialize the RecyclerView
        //rvNote = binding.rvNotes
        //rvNote.adapter = adapter

        /*updateList()
        searchView()
        swipeToDelete()
        chooseLayout()
        insertListeners()*/ // Handle click listeners
    }

    /*private fun getSharedPref() {
        val isLinearLayout = sharedPreferences.getBoolean("layout", true)
        viewModel.isLinearLayoutManager = isLinearLayout
    }*/

    /*private fun searchView() {
        val searchView: SearchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val list = viewModel.search("%$newText%")
                list.observe(this@MainActivity, {
                    adapter.submitList(it)
                })
                return true
            }

        })
    }*/

    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val layoutButton = menu?.findItem(R.id.switch_layout)
        setIcon(layoutButton)

        return true
    }*/

  /*  override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.switch_layout -> {
                // Sets isGridLayoutManager to the opposite value
                viewModel.isLinearLayoutManager = !viewModel.isLinearLayoutManager
                sharedPreferences.edit().putBoolean("layout", viewModel.isLinearLayoutManager)
                    .apply()
                // Sets layout and icon
                chooseLayout()
                setIcon(item)

                return true
            }
            //  Otherwise, do nothing and use the core event handling
            else -> super.onOptionsItemSelected(item)
        }
    }*/

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE_OK && resultCode == Activity.RESULT_OK) {
            //rvNote.adapter = adapter
            //updateList()
        }
    }*/

    /*private fun setToolbar() {
        //toolbar = binding.tbMain
        toolbar.title = ""
        setSupportActionBar(toolbar)
    }*/

    /**
     * Update the list adapter
     */
    /*private fun updateList() {
        val list = viewModel.getAll
        list.observe(this, {
            if (it.isEmpty()) {
                binding.emptyInclude.emptyState.visibility = View.VISIBLE
            } else {
                binding.emptyInclude.emptyState.visibility = View.GONE
                binding.rvNotes.visibility = View.VISIBLE
            }
            adapter.submitList(it)
        })
    }*/

    /**
     * Switches the LayoutManager
     */
    /*private fun chooseLayout() {
        if (viewModel.isLinearLayoutManager) {
            rvNote.layoutManager = LinearLayoutManager(this)
        } else {
            rvNote.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }*/

    /**
     * Set menu icon for layout and theme
     */
    /*private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null) return
        menuItem.icon = if (viewModel.isLinearLayoutManager)
            ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        else {
            ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
        }
    }*/

    /**
     * Fab clickListener
     */
    /*private fun insertListeners() {
        binding.fabNew.setOnClickListener {
            startActivityForResult(
                Intent(this, AddNoteActivity::class.java),
                RESULT_CODE_OK,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            )
        }
    }*/

    /**
     * Called whenever a note is clicked by the user
     */
    /*private fun clickedListItem(note: NoteEntity) {
        val intent = Intent(this, AddNoteActivity::class.java).also {
            it.putExtra(AddNoteActivity.TASK_ID, note.id)
        }
        startActivityForResult(intent, RESULT_CODE_OK)
    }*/

    /**
     * Handles the swipe on recycler view to delete item
     */
    /*private fun swipeToDelete() {
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
                val item = adapter.currentList[position]
                viewModel.delete(item)
                updateList()
                chooseLayout()

                Snackbar.make(
                    binding.root,
                    resources.getString(R.string.deleted),
                    Snackbar.LENGTH_LONG
                ).setAnchorView(binding.fabNew)
                    .apply {
                        setAction(resources.getString(R.string.undo)) {
                            viewModel.insert(item)
                            updateList()
                            chooseLayout()
                        }
                        show()
                    }
            }

        }).attachToRecyclerView(rvNote)
    }*/

    /**
     * Creates transition animations
     */
    /*private fun animationTransitions() {
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            // set the transition to be shown when the user enters this activity
            enterTransition = Explode()
            enterTransition.duration = 250
        }
    }*/

    /*companion object {
        private const val RESULT_CODE_OK = 1000
    }*/
}