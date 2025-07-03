package com.example.denettask.domain.usecase

import com.example.denettask.domain.model.Node
import com.example.denettask.domain.repository.TreeRepository
import jakarta.inject.Inject

class GetRootNodeUseCase @Inject constructor(
    private val repository: TreeRepository
) {
    suspend operator fun invoke(): Node = repository.getRoot()
}
