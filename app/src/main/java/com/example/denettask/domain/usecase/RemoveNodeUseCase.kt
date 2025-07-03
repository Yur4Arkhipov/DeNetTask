package com.example.denettask.domain.usecase

import com.example.denettask.domain.model.Node
import com.example.denettask.domain.repository.TreeRepository
import javax.inject.Inject

class RemoveNodeUseCase @Inject constructor(
    private val repository: TreeRepository
) {
    suspend operator fun invoke(node: Node): Boolean = repository.removeNode(node)
}