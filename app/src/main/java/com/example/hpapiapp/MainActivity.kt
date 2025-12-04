package com.example.hpapiapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCharacterById = findViewById<Button>(R.id.btnCharacterById)
        val btnStaff = findViewById<Button>(R.id.btnStaff)
        val btnHouseStudents = findViewById<Button>(R.id.btnHouseStudents)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnCharacterById.setOnClickListener {
            startActivity(Intent(this, CharacterByIdActivity::class.java))
        }

        btnStaff.setOnClickListener {
            startActivity(Intent(this, StaffActivity::class.java))
        }

        btnHouseStudents.setOnClickListener {
            startActivity(Intent(this, HouseStudentsActivity::class.java))
        }

        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}
