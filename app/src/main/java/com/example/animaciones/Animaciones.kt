package com.example.animaciones

// Importaciones generales de Compose y Runtime (State Management)
import androidx.compose.animation.* // Para AnimatedVisibility, fadeIn, fadeOut, expandVertically, shrinkVertically, slideInVertically, scaleIn, AnimatedContent
import androidx.compose.animation.core.* // Para animateColorAsState, tween, spring, infiniteRepeatable, RepeatMode, LinearEasing, animateDpAsState
import androidx.compose.foundation.background // Para el modificador .background
import androidx.compose.foundation.clickable // Para el modificador .clickable (en el GamePrototype)
import androidx.compose.foundation.layout.* // Para Column, Row, Box, Spacer, Arrangement
import androidx.compose.foundation.lazy.LazyColumn // Para el Composable Contenedor
import androidx.compose.foundation.shape.CircleShape // Para el enemigo en el GamePrototype
import androidx.compose.foundation.shape.RoundedCornerShape // Para .clip y formas
import androidx.compose.material3.* // Para Button, Text, CircularProgressIndicator, Divider
import androidx.compose.runtime.* // Para @Composable, remember, mutableStateOf, LaunchedEffect, by, val
import androidx.compose.ui.Alignment // Para Alignment.Center, Alignment.CenterHorizontally, Alignment.BottomStart
import androidx.compose.ui.Modifier // Para el objeto Modifier
import androidx.compose.ui.draw.clip // Para el modificador .clip
import androidx.compose.ui.graphics.Color // Para Color.Blue, Color.Red, etc.
import androidx.compose.ui.text.font.FontWeight // Para FontWeight.Bold
import androidx.compose.ui.text.style.TextAlign // Para TextAlign.Center
import androidx.compose.ui.unit.dp // Para valores de tamaño y padding (16.dp, 200.dp, etc.)
import androidx.compose.ui.unit.sp // Para valores de tamaño de fuente
import androidx.compose.ui.unit.times // Para usar 'with' en AnimatedContent (fadeIn with fadeOut)


import kotlinx.coroutines.delay

enum class AppState { LOADING, CONTENT, ERROR }
enum class GameState { IDLE, PLAYING, GAME_OVER }

@Composable
fun Animaciones() {
    var visible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { visible = !visible },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(if (visible) "Ocultar Cuadro" else "Mostrar Cuadro")
        }

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Blue)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Text(
                    "¡Hola!",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    fontSize = 24.sp
                )
            }
        }
    }
}