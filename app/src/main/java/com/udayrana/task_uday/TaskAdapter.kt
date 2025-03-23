package com.udayrana.task_uday

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udayrana.task_uday.databinding.TaskItemBinding

class TaskAdapter(val taskList: MutableList<Task>, val clickListener: ClickListener) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = taskList[position]

        holder.binding.textViewTaskDescription.text = task.description
        if (task.isHighPriority) {
            holder.binding.imageViewHighPriority.visibility = View.VISIBLE
        }

        holder.binding.imageViewDeleteTask.setOnClickListener {
            clickListener.deleteTask(position)
        }
        holder.binding.imageViewUpdateTask.setOnClickListener {
            clickListener.selectTask(position)
        }
    }
}