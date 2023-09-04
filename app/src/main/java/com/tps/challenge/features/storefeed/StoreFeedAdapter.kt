package com.tps.challenge.features.storefeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tps.challenge.R
import com.tps.challenge.Recipe
import com.tps.challenge.School

/**
 * A RecyclerView.Adapter to populate the screen with a store feed.
 */
class StoreFeedAdapter : RecyclerView.Adapter<StoreItemViewHolder>() {

    private var storeData = emptyList<School>()

    private var recipeData = emptyList<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        return StoreItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_store, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        //  TODO("Not yet implemented")

        val recipe = recipeData[position]

        with(holder.itemView) {
            findViewById<TextView>(R.id.name).text = recipe.calories
            findViewById<TextView>(R.id.description).text = recipe.description
            Glide.with(context)
                .load(recipe.image)
                .into(findViewById<ImageView>(R.id.image))
        }
    }

    override fun getItemCount(): Int {
        return recipeData.size
    }

    fun updateData(newData: List<Recipe>) {
        recipeData = newData
        notifyDataSetChanged()
    }

}

/**
 * Holds the view for the Store item.
 */
class StoreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)



