package com.example.todo_jetpack.data

import androidx.room.Database
import com.example.todo_jetpack.data.models.ToDoTask

@Database(entities = [ToDoTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase {

    abstract fun toDoDao(): ToDoDao
}