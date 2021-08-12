package com.arenas.dafood.data.model

import android.database.Observable
import com.google.gson.annotations.SerializedName

data class FavoriteList (
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("default")
    val default : String,
    @SerializedName("owner")
    val owner : Owner,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("visibility")
    val visibility : String,
    @SerializedName("products")
    val products : Map<String,Product>
        )
