package com.example.belajartodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.belajartodolist.api.RetrofitHelper
import com.example.belajartodolist.api.TodoApi
import com.example.belajartodolist.api.TodoData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTodo : AppCompatActivity() {

    lateinit var btnSubmit : Button
    lateinit var etTitle : EditText
    lateinit var etDescription : EditText

    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InJtdXZrYXBxeXFkYWpvbHpwZ3hyIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NjU3MzAzNzUsImV4cCI6MTk4MTMwNjM3NX0.pMduV5C5Djf5TZt6ewDTkTZeUAfbRlk6BXfN6z_GV94"
    val token = "Bearer $apiKey"

    val todoApi = RetrofitHelper.getInstance().create(TodoApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo)

        etTitle = findViewById(R.id.et_title_create_todo)
        etDescription = findViewById(R.id.et_description_create_todo)
        btnSubmit = findViewById(R.id.btn_submit_create_todo)

        btnSubmit.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {

                val data = TodoData(title = etTitle.text.toString(), description = etDescription.text.toString())
                val response = todoApi.create(token = token, apiKey = apiKey, todoData = data)

                Toast.makeText(
                    applicationContext,
                    "Berhasil membuat Todo!",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
}