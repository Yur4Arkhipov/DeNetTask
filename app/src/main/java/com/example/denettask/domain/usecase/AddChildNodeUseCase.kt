package com.example.denettask.domain.usecase

import com.example.denettask.domain.model.Node
import com.example.denettask.domain.repository.TreeRepository
import jakarta.inject.Inject

class AddChildNodeUseCase @Inject constructor(
    private val repository: TreeRepository
) {
    suspend operator fun invoke(parent: Node): Node = repository.addChild(parent)
}