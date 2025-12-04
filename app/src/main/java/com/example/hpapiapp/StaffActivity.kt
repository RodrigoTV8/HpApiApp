package com.example.hpapiapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.hpapiapp.api.HpApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StaffActivity : AppCompatActivity() {

    private lateinit var tvStaff: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Professores"

        tvStaff = findViewById(R.id.tvStaff)
        progressBar = findViewById(R.id.progressBarStaff)

        findViewById<Button>(R.id.btnLoadStaff).setOnClickListener {
            loadStaff()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun loadStaff() {
        progressBar.visibility = View.VISIBLE
        tvStaff.text = ""

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val staffList = withContext(Dispatchers.IO) {
                    HpApiClient.service.getStaff()
                }

                val nomes = staffList.joinToString(separator = "\n") { it.name }
                tvStaff.text = if (nomes.isEmpty()) {
                    "Nenhum professor encontrado."
                } else {
                    nomes
                }
            } catch (e: Exception) {
                tvStaff.text = "Erro ao carregar professores."
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }
}
