package com.example.denettask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nodes")
data class NodeEntity(
    @PrimaryKey val name: String,
    val parentName: String? = null
)