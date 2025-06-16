package com.example.denettask.domain

interface TreeTypeConverter<T> {
    fun fromString(value: String): T?
    fun toString(value: T): String
}
