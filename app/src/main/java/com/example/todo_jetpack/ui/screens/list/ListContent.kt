package com.example.todo_jetpack.ui.screens.list

import android.os.Build
import android.util.Log
import android.view.RoundedCorner
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo_jetpack.data.models.Priority
import com.example.todo_jetpack.data.models.ToDoTask
import com.example.todo_jetpack.ui.theme.LARGE_PADDING
import com.example.todo_jetpack.ui.theme.PRIORITY_INDICATOR_SIZE
import com.example.todo_jetpack.ui.theme.TASK_ITEM_ELEVATION
import com.example.todo_jetpack.util.RequestState

@Composable
fun ListContent(
    tasks: RequestState<List<ToDoTask>>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    if (tasks is RequestState.Success) {
        if (tasks.data.isEmpty()) {
            EmptyContent()
        } else {
            DisplayTasks(
                tasks = tasks.data,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    LazyColumn {
        items(
            items = tasks,
            key = { task ->
                task.id
            }
        ) { task ->
            Log.e("TAG", "ListContent: $task", )
            TaskItem(
                toDoTask = task,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = RectangleShape,
        tonalElevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = toDoTask.priority.color
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = toDoTask.description,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
                )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(
        toDoTask = ToDoTask(0, "Title", "Some random Text", Priority.MEDIUM),
        navigateToTaskScreen = {}
    )
}