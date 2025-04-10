package cat.itb.dam.m78.dbdemo3.view


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.russhwolf.settings.Settings
import cat.itb.dam.m78.dbdemo3.model.DatabaseViewModel
import coil3.compose.AsyncImage
import java.time.LocalDateTime

@OptIn(InternalComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListScreen() {
    val viewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it){EstudiantsViewModel()} }
    val estudiants = viewModel?.estudiants
    val dbViewModel = DatabaseViewModel()
    var data: String
    if (estudiants != null) {
        var expanded by remember { mutableStateOf(false) }
        val textFieldState = rememberTextFieldState()
        Column(Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Llista d'estudiants", fontSize = 30.sp)
            LazyColumn(modifier = Modifier) {
                estudiants?.forEach{ estudiant ->
                    item {
                        Row {
                            Button(
                                modifier = Modifier.width(400.dp),
                                shape = RectangleShape,
                                onClick = {
                                    data = LocalDateTime.now().toString()
                                    dbViewModel.insertEstudiant(estudiant,data)}
                            ) {
                                Column {
                                    Text("${estudiant.name} ${estudiant.surnames}")
                                    Spacer(Modifier.height(10.dp))
                                    Text(estudiant.email)
                                    Spacer(Modifier.height(10.dp))
                                    AsyncImage(
                                        model = estudiant.photo_link,
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxWidth().padding(5.dp).height(50.dp)
                                    )
                                }
                            }
                        }
                        Spacer(Modifier.height(20.dp))
                    }
                }
            }
        }
    }
}