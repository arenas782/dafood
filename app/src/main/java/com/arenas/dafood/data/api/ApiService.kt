package com.arenas.dafood.data.api

import com.arenas.dafood.data.model.FavoriteList
import retrofit2.Response
import retrofit2.http.GET

/*
 Created by arenas on 31/5/21.
*/

interface ApiService {
    @GET("favorites.json")
    suspend fun getFavorites(): Response<List<FavoriteList>>
}
