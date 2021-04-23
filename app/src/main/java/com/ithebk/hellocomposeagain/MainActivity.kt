package com.ithebk.hellocomposeagain

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
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
                var clicks by remember {mutableStateOf(0)}
                MyApp {
                    Column(modifier = Modifier.fillMaxHeight()) {
                        Column(modifier = Modifier.weight(1f)) {
                            NewStory()
                            NameList()
                        }
                        Counter(count = clicks, updateCount = { clicks++})
                    }
                }
            }

    }
}

val square : (Int) -> Int =  { data -> data*data}

//state hoisting
@Composable
fun Counter(count: Int, updateCount: () -> Unit) {
    Button(onClick = updateCount,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (count>5)Color.Green else Color.Cyan
            )
        ) {
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
fun NameList(names: List<String> = List(20) { "Hello Android #$it" }) {
    LazyColumn() {
        items(items = names) { name ->
            Greeting(value = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun Greeting(value:String) {
    var isSelected  by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected)Color.Red else Color.Transparent)
    Text(text = value,
        style=MaterialTheme.typography.h3,
        modifier = Modifier
            .padding(24.dp)
            .background(color = backgroundColor)
            .clickable(onClick = { isSelected = !isSelected }));
}

@Preview
@Composable
fun PreviewGreeting() {
    MyApp {
        Column() {
            NewStory()
            NameList()
        }
    }
}
