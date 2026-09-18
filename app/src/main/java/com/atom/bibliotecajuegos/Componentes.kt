package com.atom.bibliotecajuegos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TituloPrincipal(texto: String) {
    Text(
        text = texto,
        fontSize = 30.sp,
        fontWeight = FontWeight.ExtraBold,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    )
}


@Composable
fun TituloSeccion(texto: String) {
    Text(
        text = texto,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}

@Composable
fun SpacerPequeño() {
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun SpacerMediano() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun SpacerGrande() {
    Spacer(modifier = Modifier.height(24.dp))
}