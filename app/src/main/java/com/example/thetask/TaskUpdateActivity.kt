package com.example.thetask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.example.thetask.databinding.ActivityTaskUpdateBinding

class TaskUpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskUpdateBinding
    private lateinit var db:TaskDatabaseHelper

    private var taskId: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TaskDatabaseHelper(this)

        taskId = intent.getIntExtra("task_id",-1)
        if (taskId == -1){
            finish()
            return
        }

        val task = db.getTaskByID(taskId)
        binding.updateTitleEditText.setText(task.title)
        binding.updateContentEditText.setText(task.content)
        binding.updatePriorityEditText.setText(task.priority)
        binding.updateDeadlineEditText.setText(task.deadline)

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val newPriority = binding.updatePriorityEditText.text.toString()
            val newDeadline = binding.updateDeadlineEditText.text.toString()
            val updateTask = Task(taskId,newTitle,newContent,newPriority,newDeadline)
            db.updateTask(updateTask)
            finish()
            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()
        }
    }
}