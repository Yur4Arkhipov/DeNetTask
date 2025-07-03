package com.example.denettask.di

import com.example.denettask.data.local.NodeDao
import com.example.denettask.data.repository.TreeRepositoryImpl
import com.example.denettask.domain.repository.TreeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideTreeRepository(nodeDao: NodeDao ): TreeRepository {
        return TreeRepositoryImpl(nodeDao = nodeDao)
    }
}