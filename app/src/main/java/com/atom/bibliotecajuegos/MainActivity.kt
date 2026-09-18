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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
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

//Pantalla pricnipal
@Preview(showBackground = true)
@Composable
fun Screen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        TituloPrincipal("BIBLIOTECA DE JUEGOS")

        SpacerMediano()

        TituloSeccion("Favoritos")
        Favoritos()

        SpacerMediano()

        TituloSeccion("Jugando Actualmente")
        // Jugando()

        SpacerMediano()

        TituloSeccion("Pendientes de Jugar")
        // Pendientes()
    }
}

///Funciones para estados de los juegos

//FAVORITOS
@Composable
fun Favoritos() {
    data class Juego(val nombre: String, val imagenUrl: String)

    val juegos = listOf(
        Juego(
            "DMC: Devil May Cry 5",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/601150/header.jpg"
        ),
        Juego(
            "Batman Arkham Asylum",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/35140/header.jpg"
        ),
        Juego(
            "Detroit Become Human",
            "https://cdn.cloudflare.steamstatic.com/steam/apps/1222140/header.jpg"
        )
    )

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
//JUGANDO

//PENDIENTES



//Tarjeta de cada juegos
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
                if (calificacion < 10){
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