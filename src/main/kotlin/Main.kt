import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
fun Cell(value: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(all = 1.dp)
            .border(width = 1.dp, color = Color.LightGray)
    ) {
        Text(value, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun Grid(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(all = 4.dp)) {
        Row {
            Cell("1", modifier = Modifier.weight(1f))
            Cell("2", modifier = Modifier.weight(1f))
            Cell("3", modifier = Modifier.weight(1f))
        }
        Row {
            Cell("4", modifier = Modifier.weight(1f))
            Cell("5", modifier = Modifier.weight(1f))
            Cell("6", modifier = Modifier.weight(1f))
        }
        Row {
            Cell("7", modifier = Modifier.weight(1f))
            Cell("8", modifier = Modifier.weight(1f))
            Cell("9", modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun Dashboard() {
    Column(modifier = Modifier.padding(all = 22.dp).border(1.dp, Color.LightGray)) {
        Row {
            Grid(modifier = Modifier.weight(1f))
            Grid(modifier = Modifier.weight(1f))
            Grid(modifier = Modifier.weight(1f))
        }
        Row {
            Grid(modifier = Modifier.weight(1f))
            Grid(modifier = Modifier.weight(1f))
            Grid(modifier = Modifier.weight(1f))
        }
        Row {
            Grid(modifier = Modifier.weight(1f))
            Grid(modifier = Modifier.weight(1f))
            Grid(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun Key(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 30.sp
    )
}

@Composable
fun Keyboard() {
    Column {
        Row(modifier = Modifier.padding(bottom = 15.dp)) {
            Key(
                "1",
                modifier = Modifier.weight(1f),
            )
            Key(
                "2",
                modifier = Modifier.weight(1f),
            )
            Key(
                "3",
                modifier = Modifier.weight(1f),
            )
        }
        Row(modifier = Modifier.padding(bottom = 15.dp)) {
            Key(
                "4",
                modifier = Modifier.weight(1f),
            )
            Key(
                "5",
                modifier = Modifier.weight(1f),
            )
            Key(
                "6",
                modifier = Modifier.weight(1f),
            )
        }
        Row(modifier = Modifier.padding(bottom = 15.dp)) {
            Key(
                "7",
                modifier = Modifier.weight(1f),
            )
            Key(
                "8",
                modifier = Modifier.weight(1f),
            )
            Key(
                "9",
                modifier = Modifier.weight(1f),
            )
        }
        Row {
            Key(
                "X",
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Dashboard()
                Keyboard()
            }
        }
    }
}

fun main() = application {
    val state = rememberWindowState(
        // Size of approx. of IPhone 11
        size = DpSize(414.dp, 896.dp),
    )
    Window(onCloseRequest = ::exitApplication, state = state) {
        App()
    }
}
