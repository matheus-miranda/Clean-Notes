package br.com.mmdevelopment.cleannotes.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mmdevelopment.cleannotes.adapter.NoteListAdapter
import br.com.mmdevelopment.cleannotes.databinding.ActivityMainBinding
import br.com.mmdevelopment.cleannotes.datasource.NoteDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvNote: RecyclerView
    private val adapter by lazy { NoteListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animationTransitions() // Call animations before inflating the layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvNote = binding.rvNotes
        rvNote.layoutManager = LinearLayoutManager(this)
        //rvNote.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        insertListeners()
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

    private fun insertListeners() {
        binding.fabNew.setOnClickListener {
            startActivityForResult(
                Intent(this, AddNoteActivity::class.java),
                RESULT_CODE_OK,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_CODE_OK) {
            rvNote.adapter = adapter
            adapter.submitList(NoteDataSource.getList())
        }
    }

    companion object {
        private const val RESULT_CODE_OK = 1000
    }
}