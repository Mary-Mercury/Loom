package com.example.loom.View

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loom.Data.Tasks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(viewModel: MainViewModel, navController: NavController) {

    val tasks by viewModel.tasks.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("newTask") }
            ) {
                Icon(Icons.Default.Add, "")
            }
        },
        topBar = {
            TopAppBar(
                title = { Text(modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = "Loom") }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(items=tasks, key={ tasks -> tasks.id }) { task ->
                taskItem(
                    task = task,
                    onRemove = { viewModel.deleteTask(task.id) },
                    onComplete = { viewModel.completeTask(task.id, !task.complete)},
                    onEdit = {
                        navController.navigate("edit/${task.id}/${task.task}") }
                )
            }
        }
    }
}

@Composable
fun taskItem(
    task: Tasks,
    onRemove: () -> Unit,
    onComplete: () -> Unit,
    onEdit: () -> Unit
) {
    val swipeDismissBoxState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.EndToStart) onRemove()
            if (it == SwipeToDismissBoxValue.StartToEnd) onEdit()
            it != SwipeToDismissBoxValue.StartToEnd
        }
    )

    SwipeToDismissBox(
        state = swipeDismissBoxState,
        backgroundContent = {
            when (swipeDismissBoxState.dismissDirection) {
                SwipeToDismissBoxValue.EndToStart ->{
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.CenterEnd)
                            .padding(horizontal = 10.dp),
                    )
                }
                SwipeToDismissBoxValue.Settled -> {

                }
                SwipeToDismissBoxValue.StartToEnd -> {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.CenterStart)
                            .padding(horizontal = 10.dp),
                        )
                }
            }
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 2.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(modifier=Modifier
                    .padding(10.dp)
                    .weight(1f),
                    text=task.task,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Justify)
                Checkbox(
                    checked = task.complete,
                    onCheckedChange = { onComplete() }
                )
            }
        }
    }
}
