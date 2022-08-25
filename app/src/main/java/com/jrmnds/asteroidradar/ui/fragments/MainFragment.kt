package com.jrmnds.asteroidradar.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jrmnds.asteroidradar.R
import com.jrmnds.asteroidradar.databinding.FragmentMainBinding
import com.jrmnds.asteroidradar.domain.model.Asteroid
import com.jrmnds.asteroidradar.ui.adapter.AsteroidAdapter
import com.jrmnds.asteroidradar.ui.listener.AsteroidClickListener
import com.jrmnds.asteroidradar.ui.viewModel.NasaMainViewModel


class MainFragment : Fragment() {

    private lateinit var asteroidAdapter: AsteroidAdapter
    private lateinit var binding: FragmentMainBinding
    private var goToDetailPage = false

    private val asteroidViewModel: NasaMainViewModel by lazy {
        val activity = requireNotNull(this.activity)

        ViewModelProvider(
            this,
            NasaMainViewModel.Factory(activity.application)
        )[NasaMainViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        configureAdapter()
        configureBinding(binding)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun configureAdapter() {
        asteroidAdapter = AsteroidAdapter(AsteroidClickListener { asteroid ->
            goToDetailPage = true
            asteroidViewModel.onClickAsteroid(asteroid)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeAsteroids()
        observeSelectedAsteroid()
    }


    private fun observeSelectedAsteroid() {
        asteroidViewModel.goToDetailPage.observe(viewLifecycleOwner){ asteroidToDetailPage ->
            if(goToDetailPage){
                findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroidToDetailPage))
                asteroidViewModel.clearGoToDetailPageField()
                goToDetailPage = false
            }
        }
    }

    private fun configureBinding(binding: FragmentMainBinding) {
        binding.lifecycleOwner = this
        binding.asteroidViewModel = asteroidViewModel
        binding.asteroidRecycler.adapter = asteroidAdapter
    }

    private fun observeAsteroids() {
        asteroidViewModel.asteroids.observe(viewLifecycleOwner) { asteroid ->
            asteroid.apply {
                asteroidAdapter.submitList(this)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        asteroidViewModel.onSelectedOption(
            when (item.itemId) {
                R.id.show_all_menu -> {
                    getString(R.string.next_week_asteroids)
                }

                R.id.show_rent_menu -> {
                    getString(R.string.today_asteroids)
                }

                else -> {
                    getString(R.string.saved_asteroids)
                }
            }
        )
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.invalidateAll()
    }
}