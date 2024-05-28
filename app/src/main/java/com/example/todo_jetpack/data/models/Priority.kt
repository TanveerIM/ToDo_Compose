package com.example.todo_jetpack.data.models

import androidx.compose.ui.graphics.Color
import com.example.todo_jetpack.ui.theme.HighPriorityColor
import com.example.todo_jetpack.ui.theme.LowPriorityColor
import com.example.todo_jetpack.ui.theme.MediumPriorityColor
import com.example.todo_jetpack.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}