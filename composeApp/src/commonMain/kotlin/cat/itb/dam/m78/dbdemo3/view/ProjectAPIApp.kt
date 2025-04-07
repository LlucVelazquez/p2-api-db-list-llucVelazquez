package cat.itb.dam.m78.dbdemo3.view
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

@Serializable
data class CounterStrike(
    val id: String,
    val name: String,
    val description: String,
    val rarity: Rarity,
    val collections: List<Collections>,
    val team: Team,
    val market_hash_name: String,
    val image: String
)
@Serializable
data class Rarity(
    val name: String
)
@Serializable
data class Collections(
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
