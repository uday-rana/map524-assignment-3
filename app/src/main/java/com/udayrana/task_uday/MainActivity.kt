package com.udayrana.task_uday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.udayrana.task_uday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TaskAdapter
    var taskList: MutableList<Task> = mutableListOf(
        Task("Walk dogs", true),
        Task("Feed cats", false),
    )

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
    }

    override fun deleteTask(position: Int) {
        taskList.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    override fun selectTask(position: Int) {
        TODO("Not yet implemented")
    }

    fun addTask() {
        TODO("Not yet implemented")
    }

    fun updateTask() {
        TODO("Not yet implemented")
    }
}