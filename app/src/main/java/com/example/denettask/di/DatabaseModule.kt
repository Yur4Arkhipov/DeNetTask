package com.example.denettask.di

import android.content.Context
import androidx.room.Room
import com.example.denettask.data.local.Database
import com.example.denettask.data.local.NodeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "database"
        ).build()
    }

    @Provides
    fun provideNodeDao(db: Database): NodeDao {
        return db.nodeDao()
    }
}