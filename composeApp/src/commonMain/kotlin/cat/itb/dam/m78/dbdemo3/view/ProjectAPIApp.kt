package cat.itb.dam.m78.dbdemo3.view
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner

@Serializable
data class CounterStrike(
    val id: String,
    val name: String,
    val description: String,
    val rarity: Rarity,
    val collections: Collection,
    val team: Team,
    val market_hash_name: String,
    val image: String
)
@Serializable
data class Rarity(
    val name: String
)
@Serializable
data class Collection(
    val collections: List<ArrayObj>
)
@Serializable
@SerialName("0")
data class ArrayObj(
    val name: String
)
@Serializable
data class Team(
    val name: String
)

class CSItemsViewModel : ViewModel() {
    var skins by mutableStateOf<List<CounterStrike>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            skins = CounterStrikeApi.listSkins()
        }
    }
    fun printSkins(){viewModelScope.launch(Dispatchers.Default) { skins = CounterStrikeApi.listSkins() }}
}

object CounterStrikeApi {
    val url = "https://bymykel.github.io/CSGO-API/api/es-ES/agents.json"
    val client = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun listSkins() = client.get(url).body<List<CounterStrike>>()
}
@OptIn(InternalComposeApi::class)
@Composable
fun ProjectAPI() {
    val viewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it){CSItemsViewModel()} }
    val skins = viewModel?.skins
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        LazyColumn(modifier = Modifier) {
            skins?.forEach{ skins ->
                item {
                    Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center) {
                            Text("Name: ${skins.name}")
                        }
                    }
                }
            }
        }
    }
}