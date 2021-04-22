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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var clicks  = remember{ mutableStateOf(0)};
            NewStory()
            ClickCounter(clicks = clicks.value, onClick = {
                clicks.value +=1;
                println(clicks);
            })
            println(square(2));
//            ClickCounter(clicks.value) {
//                clicks.value += 1
//                println(clicks)
//            }
        }
    }
}

val square : (Int) -> Int =  { data -> data*data}

@Composable
fun ClickCounter(clicks: Int, onClick: () -> Unit) {
    println("ClickCounter Called:");
    Button(onClick = onClick) {
        Text("I've been clicked $clicks times")
    }
}

@Composable
fun NewStory() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        var checked = remember{ mutableStateOf(false)};
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
            "A day wandering through the sandhills " +
                    "in Shark Fin Cove, and a few of the " +
                    "sights I saw",
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis)
        Text("Davenport, California",
        style= typography.body2)
        Text("December 2018",
            style= typography.body2)
    }

}

@Preview
@Composable
fun PreviewGreeting() {
    NewStory()
}
