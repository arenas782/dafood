package com.arenas.dafood.data.model

import com.google.gson.annotations.SerializedName

/*
 Created by arenas on 31/5/21.
*/
data class Articles(
    @SerializedName("status")
    val status : String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val Articles : List<Article>
)
