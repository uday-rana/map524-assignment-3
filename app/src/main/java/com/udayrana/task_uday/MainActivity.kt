package com.udayrana.task_uday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.udayrana.task_uday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TaskAdapter
    private var taskList: MutableList<Task> = mutableListOf(
        Task("Walk dogs", true),
        Task("Feed cats", false),
    )
    private var selectedTaskPosition = -1 // Tracks selection for updating

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TaskAdapter(taskList, this)
        binding.recyclerViewTaskList.adapter = adapter
        binding.recyclerViewTaskList.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTaskList.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        binding.buttonAddTask.setOnClickListener {
            addTask()
        }

        binding.buttonUpdateTask.setOnClickListener {
            updateTask()
        }
    }

    override fun deleteTask(position: Int) {
        // Clear form if selected task is deleted
        if (position == selectedTaskPosition) {
            binding.editTextEnterTheTodo.setText("")
            binding.switchIsHighPriority.isChecked = false
            // Disable the Update Task button
            binding.buttonUpdateTask.isEnabled = false
            // Enable the Add Task button
            binding.buttonAddTask.isEnabled = true
        }
        taskList.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    override fun selectTask(position: Int) {
        // Populate the form with the selected task’s name and priority level.
        selectedTaskPosition = position
        val selectedTask = taskList[position]
        binding.editTextEnterTheTodo.setText(selectedTask.description)
        binding.switchIsHighPriority.isChecked = selectedTask.isHighPriority

        // Disable the Add Task button
        binding.buttonAddTask.isEnabled = false

        // Enable the Update Task button
        binding.buttonUpdateTask.isEnabled = true
    }

    private fun addTask() {
        // Add the task to the list and update the RecyclerView
        binding.textInputEnterTheTodoLayout.error = ""
        val newTaskDescription = binding.editTextEnterTheTodo.text.toString()
        if (newTaskDescription.isEmpty()) {
            binding.textInputEnterTheTodoLayout.error = "Required"
            return
        }
        taskList.add(
            Task(
                newTaskDescription,
                binding.switchIsHighPriority.isChecked
            )
        )
        adapter.notifyDataSetChanged()

        // Clear all form fields and prepare new input
        binding.editTextEnterTheTodo.setText("")
        binding.switchIsHighPriority.isChecked = false
    }

    private fun updateTask() {
        // Edit the selected task with the provided form field data
        binding.textInputEnterTheTodoLayout.error = ""
        val newTaskDescription = binding.editTextEnterTheTodo.text.toString()
        if (newTaskDescription.isEmpty()) {
            binding.textInputEnterTheTodoLayout.error = "Required"
            return
        }
        taskList[selectedTaskPosition].description = newTaskDescription
        taskList[selectedTaskPosition].isHighPriority = binding.switchIsHighPriority.isChecked

        // Ensure the RecyclerView is updated with the new data.
        adapter.notifyDataSetChanged()

        // Clear all form fields and prepare for new input.
        binding.editTextEnterTheTodo.setText("")
        binding.switchIsHighPriority.isChecked = false

        selectedTaskPosition = -1

        // Disable the Update Task button
        binding.buttonUpdateTask.isEnabled = false

        // Enable the Add Task button
        binding.buttonAddTask.isEnabled = true
    }
}