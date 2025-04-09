package cat.itb.dam.m78.dbdemo3.view

/*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil3.compose.AsyncImage

@Composable
fun DetailScreen(navListScreen: () -> Unit, skin: CounterStrike) {
    //val viewModel = DatabaseViewModel()
    //val skins = viewModel.
    Scaffold(
        bottomBar = {
            Button(
                modifier = Modifier.fillMaxWidth().padding(7.dp).padding(bottom = 10.dp),
                onClick = {navListScreen() },
                shape = RectangleShape
            ) {
                Text("Back")
            }
        },
        /*floatingActionButtonPosition = {
            FloatingActionButton(
                onClick = { },
            ){
                Icon(Icons.Default.Star, contentDescription = "Preferit")
            }
        }*/
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center)
        {
            Text(skin.name, fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center )
            AsyncImage(
                model = skin.image,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            )
            Column(Modifier.padding(25.dp))
            {
                Text("Name: ${skin.name}", fontWeight = FontWeight.Bold)
                Spacer(Modifier.padding(8.dp))
                Text("Rarity: ${skin.rarity}")
                Spacer(Modifier.padding(5.dp))
                Text("Coleccio: ${skin.collections[0]}")
                Spacer(Modifier.padding(5.dp))
                Text("Team ${skin.team}")
                Spacer(Modifier.padding(5.dp))
                Text("Descripcio: ${skin.description}")
            }
        }
    }
}*/