package br.com.mmdevelopment.cleannotes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.mmdevelopment.cleannotes.databinding.ActivityAddNoteBinding
import br.com.mmdevelopment.cleannotes.extensions.format
import br.com.mmdevelopment.cleannotes.extensions.text
import com.google.android.material.datepicker.MaterialDatePicker
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
     * onClickListeners for Date and Time pickers
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

        }
    }
}