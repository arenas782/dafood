package com.arenas.dafood.data.api

import com.arenas.dafood.data.model.Article
import com.arenas.dafood.data.model.Articles
import retrofit2.Response
import retrofit2.http.GET

/*
 Created by arenas on 31/5/21.
*/

interface ApiService {
    @GET("top-headlines?country=us&apiKey=7a291f5765d347f88ca79fd689729a16")
    suspend fun getArticles(): Response<Articles>
}
