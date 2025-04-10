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
data class Estudiants(
    val id: Int,
    val name: String,
    val surnames: String,
    val email: String,
    val photo_link: String
)

class EstudiantsViewModel : ViewModel() {
    var estudiants by mutableStateOf<List<Estudiants>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            estudiants = EstudiantsApi.listEstudiants()
        }
    }
}
class GetNom(id: Int) : ViewModel(){
    var estudiant by mutableStateOf<List<Estudiants>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            estudiant = EstudiantsApi.detail(id)
        }
    }
}

object EstudiantsApi {
    val url = "https://fp.mateuyabar.com/DAM-M78/composeP2/exam/students.json"
    val client = HttpClient(){
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun listEstudiants() = client.get(url).body<List<Estudiants>>()

    suspend fun detail(id: Int) =
        client.get("$url?id=$id").body<List<Estudiants>>()
}