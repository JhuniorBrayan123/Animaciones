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
    var isBlue by remember { mutableStateOf(true) }
    // Colores objetivo: Rojo y Verde
    val targetColor = if (isBlue) Color.Red else Color.Green

    val animatedColor by animateColorAsState(
        targetValue = targetColor,
        // 🚨 MODIFICACIÓN: Duración aumentada a 2 segundos (2000 ms) 🚨
        animationSpec = tween(durationMillis = 2000),
        label = "color_animation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { isBlue = !isBlue },
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text("Cambiar Color")
        }

        // --- 1. CUADRO CON ANIMACIÓN SUAVE ---
        Text(
            text = "1. ANIMADO: Transición de 2 segundos",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(animatedColor) // Usa el valor ANIMADO
                .clip(RoundedCornerShape(8.dp))
        ) {
            Text(
                "Animación Activa",
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp)) // Espacio de separación

        // --- 2. CUADRO SIN ANIMACIÓN (INSTANTÁNEO) ---
        Text(
            text = "2. INSTANTÁNEO: Sin animación (Brusco)",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(targetColor) // Usa el valor OBJETIVO final
                .clip(RoundedCornerShape(8.dp))
        ) {
            Text(
                "Cambio Instantáneo",
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}