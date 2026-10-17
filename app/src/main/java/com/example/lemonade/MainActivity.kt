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
    LemonadeWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun LemonadeWithButtonAndImage(modifier: Modifier = Modifier) {
    // État pour l'étape actuelle (1 à 4)
    var currentStep by remember { mutableStateOf(1) }

    // Nombre de clics restants pour presser le citron (aléatoire entre 2 et 4)
    var remainingClicks by remember { mutableStateOf(0) }

    // Définition du texte et de l'image selon l'étape
    val (textResource, imageResource) = when (currentStep) {
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
            // Bande supérieure jaune citron avec "Lemonade" au centre en noir
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFCCFF00))
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Lemonade",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // Contenu principal centré
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Bouton avec un fond bleu pâle et angles légèrement arrondis
                    Button(
                        onClick = {
                            when (currentStep) {
                                1 -> {
                                    currentStep = 2
                                    remainingClicks = (2..4).random()
                                }
                                2 -> {
                                    remainingClicks--
                                    if (remainingClicks <= 0) {
                                        currentStep = 3
                                    }
                                }
                                3 -> {
                                    currentStep = 4
                                }
                                4 -> {
                                    currentStep = 1
                                }
                            }
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE3F2FD) // Bleu pâle
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp
                        )
                    ) {
                        Image(
                            painter = painterResource(imageResource),
                            contentDescription = textResource,
                            modifier = Modifier.size(150.dp)
                        )
                    }

                    // Espace de 16sp entre le bouton et le texte en dessous
                    Spacer(modifier = Modifier.height(16.dp))

                    // Texte en dessous du bouton (taille 18sp, centré)
                    Text(
                        text = textResource,
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