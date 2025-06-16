package com.example.danettask.domain

object IntTreeConverter : TreeTypeConverter<Int> {
    override fun fromString(value: String): Int? = value.toIntOrNull()
    override fun toString(value: Int): String = value.toString()
}

object StringTreeConverter : TreeTypeConverter<String> {
    override fun fromString(value: String): String = value
    override fun toString(value: String): String = value
}
