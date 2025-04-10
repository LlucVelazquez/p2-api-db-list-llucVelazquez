package cat.itb.dam.m78.dbdemo3.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.dam.m78.dbdemo3.db.MyTable
import cat.itb.dam.m78.dbdemo3.view.Estudiants

import kotlinx.coroutines.launch

class DatabaseViewModel : ViewModel() {
    val allEstudiants = mutableStateOf<List<MyTable>>(emptyList())

    init {
        _fetchAllEstudiants()
    }

    private fun _fetchAllEstudiants() {
        viewModelScope.launch {
            val myTableQueries = database.myTableQueries
            allEstudiants.value = myTableQueries.selectAll().executeAsList()
        }
    }
    fun updateAllEstudiants() { _fetchAllEstudiants()}

    fun insertEstudiant(estudiant: Estudiants, data: String) {
        viewModelScope.launch {
            val myTableQueries = database.myTableQueries
            myTableQueries.insert(estudiant.id.toLong(),data)
            _fetchAllEstudiants()
        }
    }

    fun deleteEstudiants(id: Long) {
        viewModelScope.launch {
            val myTableQueries = database.myTableQueries
            myTableQueries.delete(id)
            _fetchAllEstudiants()
        }
    }
}