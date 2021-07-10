package br.com.mmdevelopment.cleannotes

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.mmdevelopment.cleannotes.databinding.ActivityMainBinding
import br.com.mmdevelopment.cleannotes.ui.AddNoteActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {
        binding.fabNew.setOnClickListener {
            startActivity(
                Intent(this, AddNoteActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            )
        }
    }
}