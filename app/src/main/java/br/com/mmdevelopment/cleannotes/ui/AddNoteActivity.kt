package br.com.mmdevelopment.cleannotes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.mmdevelopment.cleannotes.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}