package com.atom.bibliotecajuegos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.atom.bibliotecajuegos.ui.theme.BibliotecaJuegosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BibliotecaJuegosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

// ---------- Pantalla principal ----------
@Preview(showBackground = true)
@Composable
fun Screen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TituloPrincipal("BIBLIOTECA DE JUEGOS")
                SpacerPequeño()
                Image(
                    painter = painterResource(id = R.drawable.ic_control),
                    contentDescription = "Logo",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.size(32.dp)
                )
                SpacerMediano()
            }
        }

        item {
            Column {
                TituloSeccion("Favoritos")
                Favoritos()
                SpacerMediano()
            }
        }

        item {
            Column {
                TituloSeccion("Jugando Actualmente")
                Jugando()
                SpacerMediano()
            }
        }

        item {
            Column {
                TituloSeccion("Pendientes de Jugar")
                Pendientes()
            }
        }
    }
}

//Secciones

//FAVORITOS
@Composable
fun Favoritos() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            TarjetaJuego(
                nombre = "Batman: Arkham Knight",
                imagenUrl = "https://cdn.cloudflare.steamstatic.com/steam/apps/208650/header.jpg"
            )
        }
        item {
            TarjetaJuego(
                nombre = "Call of Duty: Black Ops 3",
                imagenUrl = "https://cdn.cloudflare.steamstatic.com/steam/apps/311210/header.jpg"
            )
        }
        item {
            TarjetaJuego(
                nombre = "Detroit: Become Human",
                imagenUrl = "https://cdn.cloudflare.steamstatic.com/steam/apps/1222140/header.jpg"
            )
        }
    }
}

// JUGANDO
@Composable
fun Jugando() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            TarjetaJuego(
                nombre = "Devil May Cry 5",
                imagenUrl = "https://cdn.cloudflare.steamstatic.com/steam/apps/601150/header.jpg"
            )
        }
        item {
            TarjetaJuego(
                nombre = "Tekken 8",
                imagenUrl = "https://cdn.cloudflare.steamstatic.com/steam/apps/1778820/header.jpg"
            )
        }
        item {
            TarjetaJuego(
                nombre = "Mortal Kombat 1",
                imagenUrl = "https://cdn.cloudflare.steamstatic.com/steam/apps/1971870/header.jpg"
            )
        }
    }
}

// PENDIENTES
@Composable
fun Pendientes() {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            TarjetaJuego(
                nombre = "Batman: Arkham Origins",
                imagenUrl = "https://cdn.cloudflare.steamstatic.com/steam/apps/209000/header.jpg"
            )
        }
        item {
            TarjetaJuego(
                nombre = "Street Fighter \n6",
                imagenUrl = "https://cdn.cloudflare.steamstatic.com/steam/apps/1364780/header.jpg"
            )
        }
    }
}

//Plantilla de las tarjetas de juego
@Composable
fun TarjetaJuego(nombre: String, imagenUrl: String) {
    var calificacion by remember { mutableStateOf(5) }

    Column(
        modifier = Modifier
            .width(160.dp)
            .background(
                color = Color.DarkGray,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = imagenUrl,
            contentDescription = nombre,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(460f / 215f)
        )

        SpacerPequeño()

        Text(
            text = nombre,
            color = Color.White
        )

        SpacerPequeño()

        Text(
            text = "Calificación: $calificacion",
            color = Color.White
        )

        SpacerPequeño()

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                if (calificacion > 0) calificacion--
            }) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Disminuir"
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (calificacion < 10) calificacion++
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Aumentar"
                )
            }
        }
    }
}

