package com.jrmnds.asteroidradar.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrmnds.asteroidradar.databinding.AsteroidItemBinding
import com.jrmnds.asteroidradar.domain.model.Asteroid
import com.jrmnds.asteroidradar.ui.listener.AsteroidClickListener

class AsteroidAdapter(private val clickListener: AsteroidClickListener) :
    ListAdapter<Asteroid, AsteroidAdapter.AsteroidViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {
        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class AsteroidViewHolder(private var binding: AsteroidItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid, clickListener: AsteroidClickListener) {
            binding.asteroid = asteroid
            binding.asteroidClickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val withDataBinding: AsteroidItemBinding = AsteroidItemBinding.inflate(LayoutInflater.from(parent.context))
        return AsteroidViewHolder(withDataBinding)
    }


    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}