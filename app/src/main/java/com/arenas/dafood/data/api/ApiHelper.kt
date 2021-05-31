package com.arenas.dafood.data.api

import com.arenas.dafood.data.model.Articles
import retrofit2.Response

/*
 Created by arenas on 31/5/21.
*/
interface ApiHelper {

    suspend fun getArticles() : Response<Articles>
}