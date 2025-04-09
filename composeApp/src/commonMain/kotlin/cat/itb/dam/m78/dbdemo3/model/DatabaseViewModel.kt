package cat.itb.dam.m78.dbdemo3.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.dam.m78.dbdemo3.db.MyTable
import cat.itb.dam.m78.dbdemo3.view.CounterStrike

import kotlinx.coroutines.launch

class DatabaseViewModel : ViewModel() {
    val allSkins = mutableStateOf<List<MyTable>>(emptyList())

    init {
        _fetchAllSkins()
    }

    private fun _fetchAllSkins() {
        viewModelScope.launch {
            val myTableQueries = database.myTableQueries
            allSkins.value = myTableQueries.selectAll().executeAsList()
        }
    }
    fun updateAllSkins() { _fetchAllSkins()}

    fun insertSkin(skin: CounterStrike) {
        viewModelScope.launch {
            val myTableQueries = database.myTableQueries
            myTableQueries.insert(skin.name)
            _fetchAllSkins()
        }
    }

    fun deleteSkin(id: Long) {
        viewModelScope.launch {
            val myTableQueries = database.myTableQueries
            myTableQueries.delete(id)
            _fetchAllSkins()
        }
    }
}