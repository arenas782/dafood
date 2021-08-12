package com.arenas.dafood.data.api

import com.arenas.dafood.data.model.FavoriteList
import retrofit2.Response
import javax.inject.Inject

/*
 Created by arenas on 31/5/21.
*/
class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getFavorites(): Response<List<FavoriteList>> = apiService.getFavorites()

}