package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    LemonadeFonction(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable

fun LemonadeFonction(modifier: Modifier = Modifier) {

    // État pour l etape commence a 1

    var etape by remember { mutableIntStateOf(1) }

        // clics pour presser le citron (aleatoire entre 2 et 4)

    var click by remember { mutableIntStateOf(0) }



        // Definition du text et de l image selon l etape

    val (texte, images) = when (etape) {
        1 -> Pair(R.string.citronnier, R.drawable.lemon_tree)
        2 -> Pair(R.string.citron, R.drawable.lemon_squeeze)
        3 -> Pair(R.string.citronnade, R.drawable.lemon_drink)
        else -> Pair(R.string.verre_vide, R.drawable.lemon_restart)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
              // Bande superieure jaune citron
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFCCFF00))
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = stringResource(R.string.name),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

               // Contenu principal centre
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                      // Bouton cliquable
                    Button(
                        onClick = {
                            when (etape) {
                                1 -> {
                                    etape = 2
                                    click = (2..4).random()
                                }

                                2 -> {
                                    click--
                                    if (click <= 0) {
                                        etape = 3
                                    }
                                }

                                3 -> {
                                    etape = 4
                                }

                                4 -> {
                                    etape = 1
                                }
                            }
                        },//bords aroondis et fond bleu

                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE3F2FD)

                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp
                        )
                    ) {
                        Image(
                            painter = painterResource(images),

                            // stringResource pour transformer l'ID en String
                            contentDescription = stringResource(texte),
                            modifier = Modifier.size(150.dp)
                        )
                    }

                      // Espace de 16sp entre le bouton et le texte
                    Spacer(modifier = Modifier.height(16.dp))

                    // Texte en dessous du bouton
                    Text(
                        text = stringResource(texte),
                        fontSize = 18.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)

@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}

