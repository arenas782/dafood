package com.arenas.dafood.data.repository

import com.arenas.dafood.data.api.ApiHelper
import javax.inject.Inject

/*
 Created by arenas on 31/5/21.
*/

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getArticles() =  apiHelper.getArticles()

}