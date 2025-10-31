package com.example.todo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.Task
import java.time.LocalDateTime

@Composable
fun TodoApp() {
    var taskText by remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf<Task>() }
//    val tasks = remember { mutableStateListOf<String>() }

    fun addTask() {
        if (taskText.isNotBlank()) {
            val newTask = Task(
                id = tasks.size,
                name = taskText,
                date = LocalDateTime.now()
            )
            tasks.add(newTask)
            taskText = ""
        }
    }

    fun removeTask(taskIndex: Int) {
        tasks.removeAt(taskIndex)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text("Minha To-Do List 📝", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = taskText,
                onValueChange = { taskText = it },
                label = { Text("Digite uma tarefa") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { addTask() }) {
                Text("Adicionar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            itemsIndexed(tasks) { index, task ->
                TodoItem(task, onRemove = {removeTask(index)})
            }
        }
    }
}

@Composable
fun TodoItem(task: Task, onRemove: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(task.name)
            TextButton(onClick = onRemove) {
                Text("Remover", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}