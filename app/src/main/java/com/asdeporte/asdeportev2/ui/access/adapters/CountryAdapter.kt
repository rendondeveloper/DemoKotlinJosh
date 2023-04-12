package com.asdeporte.asdeportev2.ui.access.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asdeporte.asdeportev2.databinding.ItemCountryBinding

class CountryAdapter(private val countries: List<String>, val listener: (String) -> Unit) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding, listener)
    }

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
        holder.bind(countries[position])

    class CountryViewHolder(val binding: ItemCountryBinding, val listener: (String) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: String) {
            binding.apply {
                tvCountry.text = country
                root.setOnClickListener {
                    listener(country)
                }
            }
        }
    }
}