package com.example.danettask.domain

interface TreeTypeConverter<T> {
    fun fromString(value: String): T?
    fun toString(value: T): String
}
