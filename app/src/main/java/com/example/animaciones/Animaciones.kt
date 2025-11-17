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
    var expanded by remember { mutableStateOf(false) }

    val animatedSize by animateDpAsState(
        targetValue = if (expanded) 200.dp else 100.dp,
        animationSpec = spring(dampingRatio = 0.6f),
        label = "size_animation"
    )

    val animatedOffset by animateDpAsState(
        targetValue = if (expanded) 100.dp else 0.dp,
        animationSpec = tween(durationMillis = 600),
        label = "offset_animation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { expanded = !expanded },
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            Text(if (expanded) "Contraer" else "Expandir")
        }

        Box(
            modifier = Modifier
                .offset(x = animatedOffset, y = animatedOffset)
                .size(animatedSize)
                .background(Color.Red)
                .clip(RoundedCornerShape(8.dp))
        ) {
            Text(
                "Animado",
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        }
    }
}