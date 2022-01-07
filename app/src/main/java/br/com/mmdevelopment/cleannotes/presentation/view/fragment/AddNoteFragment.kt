package br.com.mmdevelopment.cleannotes.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.mmdevelopment.cleannotes.R
import br.com.mmdevelopment.cleannotes.core.format
import br.com.mmdevelopment.cleannotes.core.text
import br.com.mmdevelopment.cleannotes.databinding.FragmentAddNoteBinding
import br.com.mmdevelopment.cleannotes.domain.model.Note
import br.com.mmdevelopment.cleannotes.presentation.addnote.AddNoteContract
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import org.koin.android.ext.android.inject
import java.util.*


class AddNoteFragment : Fragment(), AddNoteContract.View {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private val presenter: AddNoteContract.Presenter? by inject()

    private val args: AddNoteFragmentArgs? by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter!!.setView(this)
        if (args !== null) populateFieldsIfEditingNote()
        bindListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter!!.detachView()
        _binding = null
    }

    override fun findNoteById() {
        args?.note?.let {
            presenter!!.getNoteById(it.id)
        }
    }

    override fun populateFields(note: Note) {
        binding.apply {
            toolbar.title = resources.getString(R.string.edit_note)
            tilTitle.text = note.title
            tilDescription.text = note.description.toString()
            tilDate.text = note.date.toString()
            tilTime.text = note.time.toString()
        }
    }

    private fun populateFieldsIfEditingNote() {
        args?.note?.let {
            presenter!!.getNoteById(it.id)
        }
    }

    private fun bindListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.tilDate.editText?.setOnClickListener {
            getDate()
        }

        binding.tilTime.editText?.setOnClickListener {
            getTime()
        }

        binding.fabCreate.setOnClickListener {
            validateFields()
            saveNote()
        }
    }

    private fun saveNote() {
        val note = Note(
            id = args?.note?.id ?: 0,
            title = binding.tilTitle.text,
            description = binding.tilDescription.text,
            date = binding.tilDate.text,
            time = binding.tilTime.text,
        )
        presenter!!.insertNote(note)
        findNavController().popBackStack()
    }

    private fun getTime() {
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .build()
        timePicker.addOnPositiveButtonClickListener {
            val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
            val minute =
                if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
            binding.tilTime.text = "$hour:$minute"
        }
        timePicker.show(parentFragmentManager, null)
    }

    private fun getDate() {
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        datePicker.addOnPositiveButtonClickListener { date ->
            val timeZone = TimeZone.getDefault()
            val offset = timeZone.getOffset(Date().time) * -1
            binding.tilDate.text = Date(date + offset).format()
        }
        datePicker.show(parentFragmentManager, "DATE_PICKER_TAG")
    }

    /**
     * Check if title is empty and set placeholder string
     */
    private fun validateFields() {
        if (binding.tilTitle.text.trim().isEmpty()) {
            binding.tilTitle.text = resources.getString((R.string.unnamed_note))
        }
    }
}