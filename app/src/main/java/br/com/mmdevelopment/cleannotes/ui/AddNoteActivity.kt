package br.com.mmdevelopment.cleannotes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.mmdevelopment.cleannotes.databinding.ActivityAddNoteBinding
import br.com.mmdevelopment.cleannotes.datasource.NoteDataSource
import br.com.mmdevelopment.cleannotes.extensions.format
import br.com.mmdevelopment.cleannotes.extensions.text
import br.com.mmdevelopment.cleannotes.model.Note
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
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
            val note = Note(
                title = binding.tilTitle.text,
                description = binding.tilDescription.text,
                date = binding.tilDate.text,
                time = binding.tilTime.text
            )
            NoteDataSource.insertNote(note)
        }
    }
}