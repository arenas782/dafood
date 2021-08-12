package com.arenas.dafood

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arenas.dafood.data.model.FavoriteList
import com.arenas.dafood.data.model.Product
import com.arenas.dafood.databinding.ActivityMainBinding
import com.arenas.dafood.ui.main.adapter.CollectionsAdapter
import com.arenas.dafood.ui.main.adapter.MainAdapter
import com.arenas.dafood.ui.main.viewmodel.MainViewModel
import com.arenas.dafood.utils.Status
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel : MainViewModel by viewModels()
    private lateinit var adapter: MainAdapter
    private lateinit var collectionsAdapter: CollectionsAdapter
    private lateinit var binding : ActivityMainBinding

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)



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
    }

    private fun setupObservers() {
        mainViewModel.getFavorites().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.collections.visibility = View.VISIBLE

                        binding.shimmerRecyclerView.visibility = View.INVISIBLE
                        binding.shimmerRecyclerViewCollections.visibility = View.INVISIBLE


                        resource.data?.let { products -> addProducts(products) }
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE

                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.shimmerRecyclerView.visibility = View.VISIBLE
                        binding.shimmerRecyclerViewCollections.visibility = View.VISIBLE

                        binding.recyclerView.visibility = View.INVISIBLE
                        binding.collections.visibility = View.INVISIBLE
                    }
                }
            }
        })
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
        binding.textViewAllFavorites.text = getString(R.string.all_my_favorites, productsList.size)
    }

}