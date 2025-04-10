package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color

@Composable
fun bottomNavBar(navViewModel: NavigationViewModel) {
    val selectedNavigationIndex = rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            selected = selectedNavigationIndex.intValue == 0,
            onClick = {
                selectedNavigationIndex.intValue = 0
                navViewModel.navTo(Screen.ListScreen)
            },
            icon = {
                Icon(imageVector = Icons.Default.Home, contentDescription = "Casita")
            },
            label = {
                Text(
                    text = "Home",
                    color = if (0 == selectedNavigationIndex.intValue)
                        Color.Black
                    else Color.Gray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.surface,
                indicatorColor = MaterialTheme.colorScheme.primary
            )

        )
        NavigationBarItem(
            selected = selectedNavigationIndex.intValue == 1,
            onClick = {
                selectedNavigationIndex.intValue = 1
                navViewModel.navTo(Screen.FaltesScreen)
            },
            icon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "cosa")
            },
            label = {
                Text(
                    text = "Faltes",
                    color = if (1 == selectedNavigationIndex.intValue)
                        Color.Black
                    else Color.Gray
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.surface,
                indicatorColor = MaterialTheme.colorScheme.primary
            )

        )
    }
}