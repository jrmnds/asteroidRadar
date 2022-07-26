package com.jrmnds.asteroidradar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jrmnds.asteroidradar.R
import com.jrmnds.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureBinding(inflater)
        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater) {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }
}