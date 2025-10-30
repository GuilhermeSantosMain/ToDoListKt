package com.example.todo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TodoApp() {
    var taskText by remember { mutableStateOf("") }
    var tasks by remember { mutableStateOf(listOf<String>()) }

    fun addTask() {
        if (taskText.isNotBlank()) {
            tasks = tasks + taskText
            taskText = ""
        }
    }

    fun removeTask(task: String) {
        tasks = tasks - task
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
            items(tasks) { task ->
                TodoItem(task, onRemove = {removeTask(task)})
            }
        }
    }
}

@Composable
fun TodoItem(task: String, onRemove: () -> Unit) {
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
            Text(task)
            TextButton(onClick = onRemove) {
                Text("Remover", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}