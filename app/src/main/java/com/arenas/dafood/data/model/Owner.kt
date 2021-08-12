package com.arenas.dafood.data.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("name")
    val name : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("linioId")
    val linioId : String,
)
