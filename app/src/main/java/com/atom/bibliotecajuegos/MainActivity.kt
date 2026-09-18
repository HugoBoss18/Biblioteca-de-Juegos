package com.atom.bibliotecajuegos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.atom.bibliotecajuegos.ui.theme.BibliotecaJuegosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BibliotecaJuegosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen()
                }
            }
        }
    }
}

// Modelo reutilizable para cualquier lista de juegos
data class Juego(val nombre: String, val imagenUrl: String)

//Pantalla principal
@Preview(showBackground = true)
@Composable
fun Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())   // ← esta línea es la clave
    ) {
        TituloPrincipal("BIBLIOTECA DE JUEGOS")

        SpacerMediano()

        TituloSeccion("Favoritos")
        Favoritos()

        SpacerMediano()

        TituloSeccion("Jugando Actualmente")
        Jugando()

        SpacerMediano()

        TituloSeccion("Pendientes de Jugar")
        Pendientes()
    }
}

///Funciones para estados de los juegos

//FAVORITOS
@Composable
fun Favoritos() {
    val juegos = listOf(
        Juego(
            "Batman: Arkham Knight",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/208650/header.jpg"
        ),
        Juego(
            "Call of Duty: Black Ops 3",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/311210/header.jpg"
        ),
        Juego(
            "Detroit: Become Human",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/1222140/header.jpg"
        )
    )

    ListaJuegos(juegos)
}

//JUGANDO
@Composable
fun Jugando() {
    val juegos = listOf(
        Juego(
            "Devil May Cry 5",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/601150/header.jpg"
        ),
        Juego(
            "Tekken 8",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/1778820/header.jpg"
        ),
        Juego(
            "Mortal Kombat 1",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/1971870/header.jpg"
        )
    )

    ListaJuegos(juegos)
}

//PENDIENTES
@Composable
fun Pendientes() {
    val juegos = listOf(
        Juego(
            "Batman: Arkham Origins",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/209000/header.jpg"
        ),
        Juego(
            "Street Fighter \n6",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/1364780/header.jpg"
        )
    )

    ListaJuegos(juegos)
}

// Fila reutilizable para cualquier lista de juegos
@Composable
fun ListaJuegos(juegos: List<Juego>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(juegos) { juego ->
            TarjetaJuego(
                nombre = juego.nombre,
                imagenUrl = juego.imagenUrl
            )
        }
    }
}

//Tarjeta de cada juego
@Composable
fun TarjetaJuego(
    nombre: String,
    imagenUrl: String
) {
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
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(140.dp)
                .height(180.dp)
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
                if (calificacion > 0) {
                    calificacion--
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Disminuir"
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (calificacion < 10) {
                    calificacion++
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Aumentar"
                )
            }
        }
    }
}