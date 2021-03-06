package com.arenas.dafood.data.model

import com.google.gson.annotations.SerializedName

/*
 Created by arenas on 31/5/21.
*/
data class Article(
    @SerializedName("author")
    val author : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("url")
    val url : String,
    @SerializedName("urlToImage")
    val urlToImage : String,
    @SerializedName("publishedAt")
    val publishedAt : String

)
