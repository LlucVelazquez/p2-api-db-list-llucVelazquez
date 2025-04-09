package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cat.itb.dam.m78.dbdemo3.model.DatabaseViewModel

@Composable
fun FavouriteScreen(){
    val dbViewModel = DatabaseViewModel()
    val skins = dbViewModel.allSkins.value
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Favourite Screen", fontSize = 30.sp, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn {
            items(skins) { skin ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp).border(1.dp, Color.Black),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        skin.text,
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = { dbViewModel.deleteSkin(skin.id) }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}