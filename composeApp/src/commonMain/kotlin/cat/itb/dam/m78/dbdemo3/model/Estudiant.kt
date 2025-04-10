package cat.itb.dam.m78.dbdemo3.model

import kotlinx.serialization.Serializable

@Serializable
data class Estudiants(
    val id: Int,
    val name: String,
    val surnames: String,
    val data: String
)