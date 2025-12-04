package com.example.hpapiapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hpapiapp.api.HpApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterByIdActivity : AppCompatActivity() {

    private lateinit var etCharacterId: EditText
    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_by_id)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Buscar por ID"

        etCharacterId = findViewById(R.id.etCharacterId)
        tvResult = findViewById(R.id.tvResult)
        progressBar = findViewById(R.id.progressBar)

        findViewById<Button>(R.id.btnSearchCharacter).setOnClickListener {
            searchCharacter()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun searchCharacter() {
        val id = etCharacterId.text.toString().trim()
        if (id.isEmpty()) {
            Toast.makeText(this, "Informe um ID", Toast.LENGTH_SHORT).show()
            return
        }

        progressBar.visibility = View.VISIBLE
        tvResult.text = ""

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val characters = withContext(Dispatchers.IO) {
                    HpApiClient.service.getCharacterById(id)
                }

                if (characters.isNotEmpty()) {
                    val c = characters[0]
                    tvResult.text = "Nome: ${c.name}\nCasa: ${c.house ?: "Sem casa"}"
                } else {
                    tvResult.text = "Nenhum personagem encontrado."
                }
            } catch (e: Exception) {
                tvResult.text = "Erro ao buscar personagem."
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }
}
