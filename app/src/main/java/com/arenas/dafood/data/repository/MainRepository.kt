package com.arenas.dafood.data.repository

import com.arenas.dafood.data.api.ApiHelper

/*
 Created by arenas on 31/5/21.
*/

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getArticles() = apiHelper.getArticles()
}