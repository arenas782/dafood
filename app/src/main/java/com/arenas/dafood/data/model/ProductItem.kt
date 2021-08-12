package com.arenas.dafood.data.model

import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("productHash") val productHash : String,
    @SerializedName("productDetails") val product : Product,

)
