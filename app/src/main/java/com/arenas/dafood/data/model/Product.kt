package com.arenas.dafood.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String,
    @SerializedName("wishListPrice") var wishListPrice : Int,
    @SerializedName("slug") var slug : String,
    @SerializedName("url") var url : String,
    @SerializedName("image") var image : String,
    @SerializedName("linioPlusLevel") var linioPlusLevel : Int,
    @SerializedName("conditionType") var conditionType : String,
    @SerializedName("freeShipping") var freeShipping : Boolean,
    @SerializedName("imported") var imported : Boolean,
    @SerializedName("active") var active : Boolean
)
