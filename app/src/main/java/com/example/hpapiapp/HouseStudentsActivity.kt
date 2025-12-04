package com.example.hpapiapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hpapiapp.api.HpApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HouseStudentsActivity : AppCompatActivity() {

    private lateinit var rgHouses: RadioGroup
    private lateinit var tvStudents: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_students)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Estudantes por Casa"

        rgHouses = findViewById(R.id.rgHouses)
        tvStudents = findViewById(R.id.tvStudents)
        progressBar = findViewById(R.id.progressBarHouse)

        findViewById<Button>(R.id.btnLoadStudents).setOnClickListener {
            loadStudents()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun loadStudents() {
        val checkedId = rgHouses.checkedRadioButtonId
        if (checkedId == -1) {
            Toast.makeText(this, "Escolha uma casa", Toast.LENGTH_SHORT).show()
            return
        }

        val house = when (checkedId) {
            R.id.rbGryffindor -> "gryffindor"
            R.id.rbSlytherin -> "slytherin"
            R.id.rbRavenclaw -> "ravenclaw"
            R.id.rbHufflepuff -> "hufflepuff"
            else -> "gryffindor"
        }

        progressBar.visibility = View.VISIBLE
        tvStudents.text = ""

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val characters = withContext(Dispatchers.IO) {
                    HpApiClient.service.getCharactersByHouse(house)
                }

                val students = characters.filter { it.hogwartsStudent == true }

                val nomes = students.joinToString("\n") { it.name }
                tvStudents.text = if (nomes.isEmpty()) {
                    "Nenhum estudante encontrado."
                } else nomes

            } catch (e: Exception) {
                tvStudents.text = "Erro ao buscar estudantes."
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }
}
