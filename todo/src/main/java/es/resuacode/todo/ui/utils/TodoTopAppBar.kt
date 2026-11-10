package es.resuacode.todo.ui.utils

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import es.resuacode.todo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTopAppBar(backArrowFlag: Boolean = false, onGoBack: () -> Unit = {}) {
    TopAppBar(
        title = { Text("To-do list app") },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
            subtitleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            if (backArrowFlag) {
                IconButton(
                    onClick = onGoBack
                ) {
                    Icon(
                        painterResource(R.drawable.arrow_back),
                        contentDescription = "arrow back",
                    )
                }

            }
        }
    )
}