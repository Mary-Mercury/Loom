package com.example.loom.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTask(viewModel: MainViewModel, navController: NavController, taskId: Int, task: String) {

    var task by remember { mutableStateOf(task) }
    var inputError by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,text="Редактирование задачи")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(Icons.Default.ArrowBack, "")
                    }
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                value = task,
                onValueChange = {
                    task = it
                },
                isError = inputError,
                label = {Text(text="Отредактируйте задачу")},
                placeholder = {Text(text="Помыть кота")},
                singleLine = true,
                supportingText = {
                    if (inputError) Text("Введите задачу!")
                }
            )
            OutlinedButton(
                onClick = {
                    if (task != "") {
                        viewModel.editTask(taskId, task)
                        navController.popBackStack()
                    } else {
                        inputError = true
                    }
                }
            ) {
                Text(text = "Сохранить")
            }
        }
    }
}
