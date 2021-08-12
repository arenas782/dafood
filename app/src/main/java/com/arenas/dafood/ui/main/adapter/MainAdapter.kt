package com.arenas.dafood.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.arenas.dafood.R
import com.arenas.dafood.data.model.Product
import com.bumptech.glide.Glide

/*
 Created by arenas on 31/5/21.
*/
class MainAdapter(private val dataSet: ArrayList<Product>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val imageViewPhoto: ImageView
        val imageViewCondition : ImageView
        val imageViewPlusLevel : ImageView
        val imageViewImported : ImageView


        init {
            // Define click listener for the ViewHolder's View.
            imageViewPhoto = view.findViewById(R.id.imageViewPhoto)
            imageViewCondition = view.findViewById(R.id.image_view_condition)
            imageViewPlusLevel = view.findViewById(R.id.image_view_plus_level)
            imageViewImported = view.findViewById(R.id.image_view_imported)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //viewHolder.textViewAuthor.text = dataSet[position].author

        Log.e("Condition",dataSet[position].conditionType)
        dataSet[position].apply {
            when (this.conditionType){
                "new" -> {
                    viewHolder.imageViewCondition.setImageDrawable(AppCompatResources.getDrawable(viewHolder.imageViewCondition.context,R.drawable.nd_ic_new_30))
                }
                "refurbished" -> {
                    viewHolder.imageViewCondition.setImageDrawable(AppCompatResources.getDrawable(viewHolder.imageViewCondition.context,R.drawable.nd_ic_refurbished_30))
                }
                else -> {
                    viewHolder.imageViewCondition.setImageDrawable(AppCompatResources.getDrawable(viewHolder.imageViewCondition.context,R.drawable.nd_ic_refurbished_30))
                }
            }

            when (this.linioPlusLevel){
                0 -> {
                    viewHolder.imageViewPlusLevel.visibility = View.GONE
                }
                1 -> {
                    viewHolder.imageViewPlusLevel.setImageDrawable(AppCompatResources.getDrawable(viewHolder.imageViewPlusLevel.context,R.drawable.nd_ic_plus_30))
                }
                2 -> {
                    viewHolder.imageViewPlusLevel.setImageDrawable(AppCompatResources.getDrawable(viewHolder.imageViewPlusLevel.context,R.drawable.nd_ic_plus_48_30))
                }
                else -> {
                    viewHolder.imageViewPlusLevel.visibility = View.GONE
                }
            }
            if (!this.imported){
                viewHolder.imageViewImported.visibility = View.GONE
            }
        }



        dataSet[position].apply {

            Glide.with(viewHolder.imageViewPhoto.context)
                .load(this.image)
                .into(viewHolder.imageViewPhoto)
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
    fun addProducts(products: List<Product>) {
        this.dataSet.apply {
            clear()
            addAll(products)
        }
    }

}
