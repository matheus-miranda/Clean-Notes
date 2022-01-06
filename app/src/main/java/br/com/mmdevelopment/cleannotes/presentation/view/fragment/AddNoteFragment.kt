package br.com.mmdevelopment.cleannotes.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.mmdevelopment.cleannotes.core.text
import br.com.mmdevelopment.cleannotes.databinding.FragmentAddNoteBinding
import br.com.mmdevelopment.cleannotes.domain.model.Note
import br.com.mmdevelopment.cleannotes.presentation.addnote.AddNoteContract
import org.koin.android.ext.android.inject


class AddNoteFragment : Fragment(), AddNoteContract.View {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private val presenter: AddNoteContract.Presenter? by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter!!.detachView()
        _binding = null
    }

    private fun bindListeners() {
        binding.fabCreate.setOnClickListener {
            val note = Note(
                title = binding.tilTitle.text,
                description = binding.tilDescription.text,
                date = "10/10/2020",
                time = "14:44",
            )
            presenter!!.insertNote(note)
            findNavController().popBackStack()
        }
    }
}