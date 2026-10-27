package es.resuacode.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.resuacode.todo.ui.MainScreen
import es.resuacode.todo.ui.theme.MyApplicationTheme
import es.resuacode.todo.ui.utils.TodoTopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(
                    topBar = { TodoTopAppBar() },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainScreen(innerPadding)
                }
            }
        }
    }
}