package com.example.c_miniprojectmap_m6.model

data class MovieModel(
    val title: String,
    val type: Type,
    val year: String,
    val genre: List<Genre>,
    val synopsis: String,
    val imageUrl: String,
    val cast: List<String> = emptyList()
)