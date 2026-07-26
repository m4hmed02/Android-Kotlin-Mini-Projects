package com.example.recyclerview.Adaptor

import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.Model.Constants
import com.example.recyclerview.Model.ExampleItem
import com.example.recyclerview.R
import com.example.recyclerview.SecondActivity

class ExampleAdaptor(val context: Context, val elements: MutableList<ExampleItem>) : RecyclerView.Adapter<ExampleAdaptor.ExampleViewHolder> (){

    //we will inflate out layout in this function
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ExampleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_layout, parent, false)
        return ExampleViewHolder(view)
    }

    //bind the view holder with the data (adaptor)
    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = elements[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
    }

    //it will return the size of the element
    override fun getItemCount(): Int {
        return elements.size
    }

    inner class ExampleViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val title: TextView = view.findViewById(R.id.title)
        val description: TextView = view.findViewById(R.id.description)

        init {
            view.setOnClickListener {
                val position = adapterPosition
                val item = elements[position]

                Intent(context, SecondActivity::class.java).also {
                    it.putExtra(Constants.KEY_TITLE, item.title)
                    it.putExtra(Constants.KEY_DESCRIPTION, item.description)
                    context.startActivity(it)
                }

            }
        }
    }
}