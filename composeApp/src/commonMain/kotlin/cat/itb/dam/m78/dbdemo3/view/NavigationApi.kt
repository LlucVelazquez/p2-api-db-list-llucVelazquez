package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


sealed interface Screen {
    data object ListScreen : Screen
    data object FavouritesScreen : Screen
    data class DetailScreen(val skin: CounterStrike) : Screen
}

class NavigationViewModel : ViewModel() {
    val currentScreen = mutableStateOf<Screen>(Screen.ListScreen)
    fun navTo(screen: Screen) {currentScreen.value = screen }
}

@OptIn(InternalComposeApi::class)
@Composable
fun navigation() {
    val viewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it){ NavigationViewModel() } }
    if (viewModel != null) {
        val currentScreen = viewModel.currentScreen.value
        Scaffold(
            Modifier.fillMaxSize(),
            bottomBar = { if (currentScreen !is Screen.DetailScreen) { bottomNavBar(viewModel)}}
        ) {
            when (currentScreen) {
                Screen.ListScreen -> ListScreen(
                    navDetailsScreen = { viewModel.navTo(Screen.DetailScreen(it)) }
                )
                is Screen.FavouritesScreen -> FavouriteScreen()
                is Screen.DetailScreen -> DetailScreen(
                    navListScreen = { viewModel.navTo(Screen.ListScreen)},
                    skin = currentScreen.skin
                )
            }
        }
    }
}