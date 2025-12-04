package com.example.hpapiapp.model

data class HpCharacter(
    val id: String,
    val name: String,
    val house: String?,
    val hogwartsStudent: Boolean?,
    val hogwartsStaff: Boolean?
)