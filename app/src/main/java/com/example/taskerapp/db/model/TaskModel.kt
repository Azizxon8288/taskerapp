package com.example.taskerapp.db.model

import androidx.room.Entity

@Entity
data class TaskModel(
    val id: Int,
    var description: String,
    var color: Int
)