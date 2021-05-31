package com.arenas.dafood.ui.base


import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.arenas.dafood.data.api.ApiHelper
import com.arenas.dafood.data.repository.MainRepository
import com.arenas.dafood.ui.main.viewmodel.MainViewModel

/*
 Created by arenas on 31/5/21.
*/




class ViewModelFactory( vararg params: Any) : ViewModelProvider.NewInstanceFactory() {
    private val mParams: Array<Any> = params as Array<Any>
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> {
                MainViewModel( MainRepository(mParams[0] as ApiHelper)) as T
            }

            else -> {
                super.create(modelClass)
            }
        }
    }

}
