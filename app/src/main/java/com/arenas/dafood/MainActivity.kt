package com.arenas.dafood

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arenas.dafood.data.model.FavoriteList
import com.arenas.dafood.data.model.Product
import com.arenas.dafood.databinding.ActivityMainBinding
import com.arenas.dafood.ui.main.adapter.CollectionsAdapter
import com.arenas.dafood.ui.main.adapter.MainAdapter
import com.arenas.dafood.ui.main.viewmodel.MainViewModel
import com.arenas.dafood.utils.Resource
import com.arenas.dafood.utils.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel : MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private lateinit var collectionsAdapter: CollectionsAdapter
    private lateinit var binding : ActivityMainBinding
    private var observer : Observer<Resource<Response<List<FavoriteList>>>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        observer = Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.collections.visibility = View.VISIBLE
                        binding.shimmerRecyclerView.visibility = View.INVISIBLE
                        binding.shimmerRecyclerViewCollections.visibility = View.INVISIBLE
                        binding.textViewAllFavorites.visibility = View.VISIBLE
                        resource.data?.let { products -> addProducts(products) }
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.INVISIBLE
                        binding.buttonRetry.visibility = View.VISIBLE
                        binding.shimmerRecyclerViewCollections.visibility= View.INVISIBLE
                        binding.shimmerRecyclerView.visibility = View.INVISIBLE
                        binding.collections.visibility =  View.INVISIBLE
                        binding.textViewAllFavorites.visibility = View.INVISIBLE
                        Snackbar.make(findViewById(R.id.parent_layout),R.string.error_message, Snackbar.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        binding.textViewAllFavorites.visibility = View.VISIBLE
                        binding.shimmerRecyclerView.visibility = View.VISIBLE
                        binding.shimmerRecyclerViewCollections.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.INVISIBLE
                        binding.collections.visibility = View.INVISIBLE
                        binding.buttonRetry.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private fun setupUI() {
        adapter = MainAdapter(arrayListOf())
        collectionsAdapter = CollectionsAdapter(arrayListOf())
        GridLayoutManager(
            this,
            2,
            RecyclerView.VERTICAL,
            false
        ).apply {
            binding.recyclerView.layoutManager = this
        }

        binding.collections.layoutManager =    GridLayoutManager(
            this,
            1,
            RecyclerView.HORIZONTAL,
            false
        )
        binding.shimmerRecyclerView.showShimmerAdapter()
        binding.shimmerRecyclerViewCollections.showShimmerAdapter()
        binding.recyclerView.adapter = adapter
        binding.collections.adapter = collectionsAdapter

        binding.buttonRetry.setOnClickListener {
            observer?.let { it1 ->
                mainViewModel.getFavorites().removeObserver(it1)
                setupObservers()

            }
        }
    }

    private fun setupObservers() {
        mainViewModel.getFavorites().observe(this, observer!!)
    }

    private fun addProducts(favoriteList: Response<List<FavoriteList>>){
        val productsList : MutableList<Product> = mutableListOf()
        favoriteList.body()?.forEach {
            it.products.map { innerProducts ->
                productsList.add(innerProducts.value)
            }
        }
        collectionsAdapter.apply {
            favoriteList.body()?.let { addCollections(it)
            }
        }
        adapter.apply {
            addProducts(productsList)
        }
        binding.textViewAllFavorites.text = getString(R.string.all_my_favorites_placeholder,productsList.size)
    }

}