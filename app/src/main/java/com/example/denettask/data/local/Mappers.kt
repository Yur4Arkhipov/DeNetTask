package com.example.denettask.data.local

import com.example.denettask.domain.model.Node

fun Node.toEntity(): NodeEntity = NodeEntity(name = this.name, parentName = this.getParent()?.name)