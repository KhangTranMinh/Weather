package com.ktm.weather.presentation.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.ktm.weather.R
import com.ktm.weather.databinding.ActivityDetailsBinding
import com.ktm.weather.domain.entity.CurrentWeather
import com.ktm.weather.domain.entity.NextWeather
import com.ktm.weather.presentation.ui.base.BaseActivity
import com.ktm.weather.presentation.view.TextViewFactory
import com.ktm.weather.presentation.view.UiUtil
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var colors: IntArray
    private lateinit var colorsAlpha: IntArray

    private lateinit var itemAdapter: ItemAdapter<NextWeatherUiModel>

    private var city: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarLayout.toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            title = ""
        }

        city = intent.extras?.getString(EXTRAS_KEY_CITY) ?: ""

        initViews()
        setupTextSwitchers()
        initRecyclerView()

        updateUiByState()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        viewModel.fetchData(q = city)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun initViews() {
        colors = resources.getIntArray(R.array.mdcolor_500)
        colorsAlpha = resources.getIntArray(R.array.mdcolor_500_alpha)
        binding.toolbarLayout.tvCity.text = city
    }

    private fun setupTextSwitchers() {
        binding.contentMainLayout.tvTemperature.apply {
            setFactory(TextViewFactory(this@DetailsActivity, R.style.TemperatureTextView, true))
            setInAnimation(this@DetailsActivity, R.anim.slide_in_right)
            setOutAnimation(this@DetailsActivity, R.anim.slide_out_left)
        }
        binding.contentMainLayout.tvDescription.apply {
            setFactory(TextViewFactory(this@DetailsActivity, R.style.DescriptionTextView, true))
            setInAnimation(this@DetailsActivity, R.anim.slide_in_right)
            setOutAnimation(this@DetailsActivity, R.anim.slide_out_left)
        }
        binding.contentMainLayout.tvHumidity.apply {
            setFactory(TextViewFactory(this@DetailsActivity, R.style.HumidityTextView, false))
            setInAnimation(this@DetailsActivity, R.anim.slide_in_bottom)
            setOutAnimation(this@DetailsActivity, R.anim.slide_out_top)
        }
        binding.contentMainLayout.tvWind.apply {
            setFactory(TextViewFactory(this@DetailsActivity, R.style.WindSpeedTextView, false))
            setInAnimation(this@DetailsActivity, R.anim.slide_in_bottom)
            setOutAnimation(this@DetailsActivity, R.anim.slide_out_top)
        }
    }

    private fun initRecyclerView() {
        itemAdapter = ItemAdapter()
        val fastAdapter: FastAdapter<NextWeatherUiModel> = FastAdapter.with(itemAdapter)
        binding.contentMainLayout.recyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@DetailsActivity, LinearLayoutManager.HORIZONTAL, false
            )
            itemAnimator = DefaultItemAnimator()
            isFocusable = false
            adapter = fastAdapter
        }
    }

    private fun updateUiByState() {
        lifecycleScope.launch {
            viewModel.detailsUiState.collect { uiState ->
                when (uiState) {
                    DetailsUiState.Default -> {
                    }

                    is DetailsUiState.FetchCurrentWeatherSuccess -> {
                        updateUiCurrentWeather(uiState.currentWeather)
                    }

                    is DetailsUiState.FetchFiveDaysWeatherSuccess -> {
                        updateUiNextWeathers(uiState.nextWeathers)
                    }

                    is DetailsUiState.Error -> {
                        Toast.makeText(
                            this@DetailsActivity,
                            "No weather found with this city",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun updateUiCurrentWeather(currentWeather: CurrentWeather) {
        ensureActivityActive {
            binding.ivNoCity.visibility = View.GONE

            binding.contentMainLayout.tvTemperature.setText(
                String.format(
                    Locale.getDefault(), "%.0f°", currentWeather.temp
                )
            )
            binding.contentMainLayout.tvDescription.setText(
                UiUtil.getWeatherStatus(currentWeather.weatherId)
            )
            binding.contentMainLayout.tvHumidity.setText(
                String.format(
                    Locale.getDefault(), "%d%%", currentWeather.humidity
                )
            )
            binding.contentMainLayout.tvWind.setText(
                String.format(
                    Locale.getDefault(),
                    getString(R.string.label_wind_unit),
                    currentWeather.windSpeed
                )
            )
            binding.contentMainLayout.viewAnimation.apply {
                setAnimation(UiUtil.getWeatherAnimation(currentWeather.weatherId))
                playAnimation()
            }
            binding.contentMainLayout.nestedScrollView.visibility = View.VISIBLE
        }
    }

    private fun updateUiNextWeathers(nextWeathers: List<NextWeather>) {
        ensureActivityActive {
            binding.ivNoCity.visibility = View.GONE

            val uiModels = arrayListOf<NextWeatherUiModel>()
            nextWeathers.forEachIndexed { index, item ->
                val color = colors[index]
                val colorAlpha = colorsAlpha[index]
                val newCalendar: Calendar = UiUtil.addDays(Calendar.getInstance(), index)
                uiModels.add(
                    NextWeatherUiModel(
                        dt = item.dt,
                        temp = item.temp,
                        minTemp = item.minTemp,
                        maxTemp = item.maxTemp,
                        weatherId = item.weatherId,
                        timestampStart = UiUtil.getStartOfDayTimestamp(newCalendar),
                        timestampEnd = UiUtil.getEndOfDayTimestamp(newCalendar),
                        color = color,
                        colorAlpha = colorAlpha
                    )
                )
            }
            itemAdapter.apply {
                clear()
                uiModels.removeFirst()
                add(uiModels)
            }
        }
    }

    companion object {
        const val EXTRAS_KEY_CITY = "EXTRAS_KEY_CITY"
    }
}