package com.arenas.dafood.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.arenas.dafood.data.repository.MainRepository
import com.arenas.dafood.utils.Resource
import kotlinx.coroutines.Dispatchers

/*
 Created by arenas on 31/5/21.
*/

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getArticles() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getArticles()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}