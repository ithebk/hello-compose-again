package com.ithebk.hellocomposeagain

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ithebk.hellocomposeagain.ui.theme.HelloComposeAgainTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(square(2))
            setContent {
                val clicks = remember {mutableStateOf(0)}
                MyApp {
                    Column() {
                        NewStory()
                        MyScreenContent()
                        Counter(count = clicks.value, updateCount = { clicks.value++})
                    }
                }
            }

    }
}

val square : (Int) -> Int =  { data -> data*data}

//state hoisting
@Composable
fun Counter(count: Int, updateCount: () -> Unit) {
    Button(onClick = updateCount) {
        Text(text = "You have been clicked $count times")
    }
}

@Composable
fun NewStory() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        val checked = remember{ mutableStateOf(false)}
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = "Header",
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop,

        )
        Checkbox(checked = checked.value, onCheckedChange = {check -> checked.value=check})
        Spacer(Modifier.height(16.dp))
        Text(
            "A day wandering through the sand hills " +
                    "in Shark Fin Cove, and a few of the " +
                    "sights I saw",
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis)
        Text("Davenport, California",
        style= typography.body2)
        Text("December",
            style= typography.body2,
            modifier = Modifier.padding(top=16.dp))

    }

}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    HelloComposeAgainTheme() {
        Surface(color = Color.Yellow) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names : List<String> = listOf("Hello", "Compose", "How are you")) {
    Column {
        for (name in names) {
            Greeting(name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Greeting(value:String) {
    Text(text = value);
}

@Preview
@Composable
fun PreviewGreeting() {
    MyApp {
        Column() {
            NewStory()
            MyScreenContent()
        }
    }
}
