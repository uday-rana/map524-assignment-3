package com.udayrana.task_uday

interface ClickListener {
    fun displayTask(position: Int)
    fun deleteTask(position: Int)
    fun updateTask(position: Int)
}