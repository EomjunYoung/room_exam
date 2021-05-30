package com.example.room_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.room_exam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
        ).build()

        db.todoDao().getAll.observe(this, Observer { todos ->

        binding.resultText.text = todos.toString()

        })


        binding.addButton.setOnClickListener {
            db.todoDao().insert(Todo(binding.todoEdit.text.toString()))
        }

    }
}