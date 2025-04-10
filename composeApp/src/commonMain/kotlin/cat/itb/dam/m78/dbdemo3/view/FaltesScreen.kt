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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.dam.m78.dbdemo3.model.DatabaseViewModel

@OptIn(InternalComposeApi::class)
@Composable
fun FaltesScreen(){
    val dbViewModel = DatabaseViewModel()
    val estudiant = dbViewModel.allEstudiants.value
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Faltes", fontSize = 30.sp, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn {
            items(estudiant) { estudiant->
                val viewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it){GetNom(estudiant.id.toInt())} }
                val idEstudiant = estudiant.id.toInt() - 1
                val nom = viewModel?.estudiant?.get(idEstudiant)?.name
                val cognom = viewModel?.estudiant?.get(idEstudiant)?.surnames
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp).border(1.dp, Color.Black),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        nom.toString(),
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        cognom.toString(),
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    /*Text(
                        estudiant.id.toString(),
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )*/
                    Text(
                        estudiant.date,
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}