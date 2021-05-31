package com.example.room_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.*
import androidx.room.Room
import com.example.room_exam.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _binding = ActivityMainBinding.inflate(layoutInflater)

//        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        var viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
        ).build()

        //TODO: 아래는 뷰모델이 아닌 직접접근할때
//        db.todoDao().getAll().observe(this, Observer { todos ->
        //TODO: 아래는 뷰모델을 통해 접근
        viewModel.getAll().observe(this, Observer { todos ->

        binding.resultText.text = todos.toString()

        })


        binding.addButton.setOnClickListener {
            //TODO: 아래는 db가 직접 insert
//            db.todoDao().insert(Todo(binding.todoEdit.text.toString()))
            //TODO: 아래는 뷰모델을 통해 insert를 지시

            GlobalScope.launch {
                viewModel.insert(Todo(binding.todoEdit.text.toString())) //insert가 suspend함수이기 떄문에 코루틴 밖에서 쓰면 안됨
            }
        }

    }
}