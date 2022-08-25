package com.jrmnds.asteroidradar.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.jrmnds.asteroidradar.R
import com.jrmnds.asteroidradar.databinding.FragmentDetailBinding
import com.jrmnds.asteroidradar.domain.model.Asteroid

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var getAsteroid: Asteroid

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        configureBinding(inflater)
        getSelectAsteroid()
        showDialogInfo()
        return binding.root
    }

    private fun configureBinding(inflater: LayoutInflater) {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
    }

    private fun getSelectAsteroid() {
        getAsteroid = DetailFragmentArgs.fromBundle(requireArguments()).selectedAsteroid
        binding.asteroid = getAsteroid
    }

    private fun showDialogInfo() {
        binding.helpButton.setOnClickListener {
            showHelpInformation()
        }
    }

    private fun showHelpInformation() {
        val builder = AlertDialog.Builder(requireActivity())
            .setMessage(getString(R.string.astronomica_unit_explanation))
            .setPositiveButton(android.R.string.ok, null)
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.invalidateAll()
    }
}