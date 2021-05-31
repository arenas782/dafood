package com.arenas.dafood.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.arenas.dafood.R
import com.arenas.dafood.data.model.Article
import com.bumptech.glide.Glide

/*
 Created by arenas on 31/5/21.
*/
class MainAdapter(private val dataSet: ArrayList<Article>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle: TextView
        val textViewAuthor: TextView
        val imageViewPhoto: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textViewTitle = view.findViewById(R.id.textViewTitle)
            textViewAuthor = view.findViewById(R.id.textViewAuthor)
            imageViewPhoto = view.findViewById(R.id.imageViewPhoto)

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
        viewHolder.textViewAuthor.text = dataSet[position].author
        viewHolder.textViewTitle.text = dataSet[position].title

        Glide.with(viewHolder.imageViewPhoto.context)
            .load(dataSet[position].urlToImage)
            .into(viewHolder.imageViewPhoto)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
    fun addUsers(users: List<Article>) {
        this.dataSet.apply {
            clear()
            addAll(users)
        }
    }

}
