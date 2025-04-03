package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.material.Button
import androidx.compose.runtime.Composable


@Composable
fun DetailScreen(details: List<String>, navListScreen: () -> Unit){

    Button(onClick = { navListScreen() }) {}
}