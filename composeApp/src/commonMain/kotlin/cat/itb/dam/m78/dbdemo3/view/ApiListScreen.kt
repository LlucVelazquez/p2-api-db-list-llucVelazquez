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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.*
import androidx.compose.ui.unit.dp

@OptIn(InternalComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navDetailsScreen: (CounterStrike) -> Unit) {
    val viewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it){CSItemsViewModel()} }
    val skins = viewModel?.skins
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("All", "Favourites")
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom) {
        BottomNavigation(windowInsets = BottomNavigationDefaults.windowInsets) {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index }
                )
            }
        }
    }
    if (skins != null) {
        var filteredSkins by remember { mutableStateOf(skins) }
        var expanded by remember { mutableStateOf(false) }
        val textFieldState = rememberTextFieldState()
        Column {
            SearchBar(
                modifier = Modifier.semantics { traversalIndex = 0f },
                inputField = {
                    SearchBarDefaults.InputField(
                        query = textFieldState.text.toString(),
                        onQueryChange = {textFieldState.edit { replace(0,length,it) }},
                        onSearch = {
                                query-> filteredSkins = skins.filter { it.name.contains(query, ignoreCase = true) }
                            expanded = false
                        },
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = it
                        },
                        placeholder = {
                            Text(text = "Search")
                        }
                    )
                },
                expanded = expanded,
                onExpandedChange = {
                    expanded = it
                },
                shape = RectangleShape
            ){}
            LazyColumn(modifier = Modifier) {
                filteredSkins.forEach{ skin ->
                    item {
                        Row {
                            Button(
                                modifier = Modifier.width(400.dp),
                                shape = RectangleShape,
                                onClick = { navDetailsScreen(skin) }
                            ) {
                                Text(skin.name)
                            }
                        }
                    }
                }
            }
        }
    }
}