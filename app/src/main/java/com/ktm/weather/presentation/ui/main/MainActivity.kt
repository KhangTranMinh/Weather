package com.ktm.weather.presentation.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktm.weather.R
import com.ktm.weather.databinding.ActivityMainBinding
import com.ktm.weather.domain.entity.City
import com.ktm.weather.presentation.ui.base.BaseActivity
import com.ktm.weather.presentation.ui.details.DetailsActivity
import com.miguelcatalan.materialsearchview.MaterialSearchView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val cityAdapter = CityAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = ""
        }

        initSearchView()
        initRecyclerView()

        updateUiByState()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        viewModel.getCities()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.searchView.isSearchOpen) {
            hideSearch()
        } else {
            super.onBackPressed()
        }
    }

    private fun initSearchView() {
        binding.searchView.apply {
            setVoiceSearch(false)
            setHint(getString(R.string.label_search_for_a_city))
            setCursorDrawable(R.drawable.custom_curosr)
            setEllipsize(true)
            setOnQueryTextListener(
                object : MaterialSearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        val city = runCatching {
                            val tokens = query.split(",")
                            when (tokens.size) {
                                1 -> {
                                    City(name = tokens[0], country = "")
                                }

                                2 -> {
                                    City(name = tokens[0], country = tokens[1])
                                }

                                else -> {
                                    null
                                }
                            }
                        }.getOrNull()
                        if (city == null) {
                            Toast.makeText(
                                this@MainActivity, "Invalid city name", Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            viewModel.saveCity(city)
                            goToDetailsPage(city.toString())
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        return false
                    }
                })
            setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
                override fun onSearchViewShown() {
                    binding.toolbar.visibility = View.GONE
                }

                override fun onSearchViewClosed() {
                    binding.toolbar.visibility = View.VISIBLE
                }
            })
        }

        binding.btnSearch.setOnClickListener {
            showSearch()
        }
    }

    private fun initRecyclerView() {
        cityAdapter.onItemClickListener = object : CityAdapter.OnItemClickListener {
            override fun onItemClicked(city: City) {
                goToDetailsPage(city.toString())
            }

            override fun onItemActionDeleteClicked(city: City) {
                viewModel.deleteCity(city)
            }
        }
        binding.apply {
            val layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.layoutManager = layoutManager
            val itemDecorator = DividerItemDecoration(
                this@MainActivity, DividerItemDecoration.VERTICAL
            )
            itemDecorator.setDrawable(
                ContextCompat.getDrawable(
                    this@MainActivity, R.drawable.divider
                )!!
            )
            recyclerView.addItemDecoration(itemDecorator)
            recyclerView.adapter = cityAdapter
        }
    }

    private fun updateUiByState() {
        lifecycleScope.launch {
            viewModel.mainUiState.collect { uiState ->
                when (uiState) {
                    MainUiState.Default -> {
                    }

                    is MainUiState.GetCitySuccess -> {
                        updateUiCities(uiState.cities)
                    }

                    is MainUiState.Error -> {
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateUiCities(cities: List<City>) {
        ensureActivityActive {
            cityAdapter.dataSet.clear()
            if (cities.isEmpty()) {
                binding.ivNoCity.visibility = View.VISIBLE
            } else {
                binding.ivNoCity.visibility = View.GONE
                cityAdapter.dataSet.addAll(cities)
            }
            cityAdapter.notifyDataSetChanged()
        }
    }

    private fun showSearch() {
        binding.toolbar.visibility = View.GONE
        binding.searchView.showSearch()
    }

    private fun hideSearch() {
        binding.toolbar.visibility = View.VISIBLE
        binding.searchView.closeSearch()
    }

    private fun goToDetailsPage(cityName: String) {
        hideSearch()
        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRAS_KEY_CITY, cityName)
        startActivity(intent)
    }
}