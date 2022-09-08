package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceAppScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceAppScreen() {
    var result by remember {
        mutableStateOf(1)
    }
    val artist = when (result) {
        1 -> {
            ArtSpaceImageText(
                image = R.drawable.budva,
                title = R.string.budva,
                artist = R.string.budva_artist,
                year = R.string.budva_year
            )
        }
        2 -> {
            ArtSpaceImageText(
                image = R.drawable.cracovia,
                title = R.string.cracovia,
                artist = R.string.cracovia_artist,
                year = R.string.cracovia_year
            )
        }
        3 -> {
            ArtSpaceImageText(
                image = R.drawable.lubiana,
                title = R.string.luibiana,
                artist = R.string.luibiana_artist,
                year = R.string.luibiana_year
            )
        }
        else -> println("Error")
    }
    ButtonArt(
        modifierButton = Modifier
            .height(30.dp)
            .width(150.dp),
        { if (result == 1) result = 3 else result-- },
        { if (result == 3) result = 1 else result++ }
    )
}

@Composable
fun ArtSpaceImageText(image: Int, title: Int, artist: Int, year: Int) {
    Column(modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .heightIn(500.dp, 530.dp)
                .wrapContentHeight(Alignment.CenterVertically)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = stringResource(title),
                modifier = Modifier
                    .border(3.dp, Color.Black, RectangleShape)
                    .padding(16.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Row {
                Surface(
                    modifier = Modifier
                        .heightIn(80.dp, 300.dp)
                        .widthIn(300.dp, 380.dp),

                    shape = RectangleShape,
                    elevation = 12.dp,

                    ) {
                    Box {
                        Text(
                            text = stringResource(title),
                            fontSize = 30.sp,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Box(modifier = Modifier.padding(top = 50.dp),) {
                        Text(
                            text = stringResource(artist)+ stringResource(year),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonArt(
    modifierButton: Modifier,
    clickPrev: () -> Unit,
    clickNext: () -> Unit
) {
    Column(
        modifier = Modifier.padding(top = 300.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = clickPrev, modifier = Modifier.size(100.dp)) {
                Text(text = "Previous", fontSize = 18.sp)
            }
            Button(onClick = clickNext, modifier = Modifier.size(100.dp)) {
                Text(text = "Next", fontSize = 18.sp)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme {
        ArtSpaceAppScreen()
    }
}