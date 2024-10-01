package com.ktm.weather.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ktm.weather.databinding.ItemCityBinding
import com.ktm.weather.domain.entity.City

class CityAdapter(
    val dataSet: ArrayList<City> = arrayListOf()
) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCityBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = dataSet[position]
        holder.binding.tvCity.text = city.name
        holder.binding.tvCountry.text = city.country
        holder.binding.main.setOnClickListener {
            onItemClickListener?.onItemClicked(city)
        }
        holder.binding.ivDelete.setOnClickListener {
            onItemClickListener?.onItemActionDeleteClicked(city)
        }
    }

    class ViewHolder(val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClicked(city: City)

        fun onItemActionDeleteClicked(city: City)
    }
}