package com.arenas.dafood.ui.main.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arenas.dafood.R
import com.arenas.dafood.data.model.FavoriteList
import com.arenas.dafood.data.model.Product
import com.bumptech.glide.Glide

/*
 Created by arenas on 31/5/21.
*/
class CollectionsAdapter(private val dataSet: ArrayList<FavoriteList>) : RecyclerView.Adapter<CollectionsAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val mainPicture: ImageView
        val secondPicture : ImageView
        val thirdPicture : ImageView
        val collectionName : TextView
        val collectionCount : TextView



        init {
            mainPicture = view.findViewById(R.id.image_view_main_picture)
            secondPicture = view.findViewById(R.id.image_view_second_picture)
            thirdPicture = view.findViewById(R.id.image_view_third_picture)
            collectionName = view.findViewById(R.id.text_view_collection_name)
            collectionCount = view.findViewById(R.id.text_view_collection_count)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.collection_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val productsList : MutableList<Product> = mutableListOf()

        dataSet[position].apply {

            this.products.map { innerProducts ->
                productsList.add(innerProducts.value)
            }

            viewHolder.collectionName.text = this.name



            viewHolder.collectionCount.text = this.products.size.toString()

            Glide.with(viewHolder.mainPicture.context)
                .load(productsList.elementAt(0).image)
                .into(viewHolder.mainPicture)

            Glide.with(viewHolder.secondPicture.context)
                .load(productsList.elementAt(1).image)
                .into(viewHolder.secondPicture)

            Glide.with(viewHolder.thirdPicture.context)
                .load(productsList.elementAt(2).image)
                .into(viewHolder.thirdPicture)
        }



        dataSet[position].apply {

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
    fun addCollections(collections: List<FavoriteList>) {
        this.dataSet.apply {
            clear()
            addAll(collections)
        }
    }

}
