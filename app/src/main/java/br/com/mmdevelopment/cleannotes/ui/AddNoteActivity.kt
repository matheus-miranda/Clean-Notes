package br.com.mmdevelopment.cleannotes.ui

import android.app.Activity
import android.os.Bundle
import android.transition.Slide
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import br.com.mmdevelopment.cleannotes.R
import br.com.mmdevelopment.cleannotes.databinding.ActivityAddNoteBinding
import br.com.mmdevelopment.cleannotes.datasource.NoteDataSource
import br.com.mmdevelopment.cleannotes.extensions.format
import br.com.mmdevelopment.cleannotes.extensions.text
import br.com.mmdevelopment.cleannotes.model.Note
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        animationTransitions()
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        getExtra()
        insertListeners()
    }

    /**
     * Populate fields if editing a note
     */
    private fun getExtra() {
        if (intent.hasExtra(TASK_ID)) {
            toolbar.title = resources.getString(R.string.edit_note)
            val taskId = intent.getIntExtra(TASK_ID, 0)
            NoteDataSource.findById(taskId)?.let {
                binding.tilTitle.text = it.title
                binding.tilDescription.text = it.description
                binding.tilDate.text = it.date
                binding.tilTime.text = it.time
            }
        }
    }

    /**
     * Set up toolbar and behavior of navigation icon
     */
    private fun setToolbar() {
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    /**
     * onClickListeners for create button, date and time pickers
     */
    private fun insertListeners() {
        binding.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener { date ->
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDate.text = Date(date + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.tilTime.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener {
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                val minute =
                    if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                binding.tilTime.text = "$hour:$minute"
            }
            timePicker.show(supportFragmentManager, null)
        }

        binding.fabCreate.setOnClickListener {
            validateFields()

            val note = Note(
                title = binding.tilTitle.text,
                description = binding.tilDescription.text,
                date = binding.tilDate.text,
                time = binding.tilTime.text,
                id = intent.getIntExtra(TASK_ID, 0)
            )
            NoteDataSource.insertNote(note)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    /**
     * Check for empty text fields before adding to list
     */
    private fun validateFields() {
        // Check if title is empty and set placeholder string
        if (binding.tilTitle.text.trim().isEmpty()) {
            binding.tilTitle.text = resources.getString((R.string.unnamed_note))
        }
    }

    /**
     * Creates transition animations
     */
    private fun animationTransitions() {
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            // set the transition to be shown when the user enters this activity
            enterTransition = Slide()
            //enterTransition.duration = 400
        }
    }

    companion object {
        const val TASK_ID = "task_id"
    }
}