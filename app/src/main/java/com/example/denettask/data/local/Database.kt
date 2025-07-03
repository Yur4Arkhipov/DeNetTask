package com.example.denettask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NodeEntity::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun nodeDao(): NodeDao
}