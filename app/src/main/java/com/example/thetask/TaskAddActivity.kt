package com.example.thetask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.thetask.databinding.ActivityTaskAddBinding

class TaskAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskAddBinding
    private lateinit var db:TaskDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabaseHelper(this)

        binding.saveButton.setOnClickListener{
            val title = binding.titleText.text.toString()
            val content = binding.contentText.text.toString()
            val priority = binding.priorityEditText.text.toString()
            val deadline = binding.deadlineEditText.text.toString()

            val task = Task(0,title,content,priority,deadline)
            db.insertTask(task)
            finish()
            Toast.makeText(this,"Task Saved", Toast.LENGTH_SHORT).show()
        }
    }
}