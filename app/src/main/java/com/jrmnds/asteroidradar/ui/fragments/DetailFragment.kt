package com.jrmnds.asteroidradar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jrmnds.asteroidradar.R
import com.jrmnds.asteroidradar.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureBinding(inflater)
        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater) {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }
}