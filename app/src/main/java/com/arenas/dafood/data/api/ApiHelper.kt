package com.arenas.dafood.data.api

/*
 Created by arenas on 31/5/21.
*/
class ApiHelper(private val apiService: ApiService) {

    suspend fun getArticles() = apiService.getArticles()
}