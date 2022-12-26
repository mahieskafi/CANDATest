package com.canda.ca.presentation.weather

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.canda.ca.BuildConfig
import com.canda.ca.R
import com.canda.ca.databinding.FragmentWeatherBinding
import com.canda.ca.domain.model.MainInfo
import com.canda.ca.domain.model.TemperatureInfo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeatherBinding
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    private lateinit var adapter: ForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ForecastAdapter()
        binding.nextDaysRecycler.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.nextDaysRecycler.adapter = adapter

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadData()
            subscribeCurrentWeatherResult()
            subscribeForecastResult()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )

    }

    private fun subscribeCurrentWeatherResult() {
        lifecycleScope.launch {
            viewModel.currentWeatherState.observe(viewLifecycleOwner) { weatherState ->
                when (weatherState) {
                    is WeatherState.IsLoading -> {
                        binding.loading.visibility = View.VISIBLE
                        binding.dataContainer.visibility = View.GONE
                    }
                    is WeatherState.ShowError -> {
                        binding.loading.visibility = View.GONE
                        binding.dataContainer.visibility = View.GONE
                        //TODO show error
                    }
                    is WeatherState.ShowData -> {
                        binding.loading.visibility = View.GONE
                        binding.dataContainer.visibility = View.VISIBLE

                        setTemperatureInfo(weatherState.data.temperatureInfo)
                        setMainInfo(weatherState.data.mainInfo[0]
                        )
                        binding.cityNameTextView.text = buildString {
                            append(weatherState.data.name)
                            append(", ")
                            append(weatherState.data.internalInfo.country)
                        }

                    }
                }
            }

        }

    }

    private fun setTemperatureInfo(
        temperatureInfo: TemperatureInfo
    ) {
        binding.currentWeatherView.humidityTextView.text = java.lang.String.format(
            Locale.getDefault(),
            "%d%%",
            temperatureInfo.humidity
        )
        binding.currentWeatherView.feelingTextView.text = java.lang.String.format(
            Locale.getDefault(),
            "%.0f°",
            temperatureInfo.feelingTemperature
        )
        binding.currentWeatherView.tempTextView.text = java.lang.String.format(
            Locale.getDefault(),
            "%.0f°",
            temperatureInfo.temperature
        )
    }

    private fun setMainInfo(
        mainInfo: MainInfo
    ) {
        binding.currentWeatherView.descriptionTextView.text =
            mainInfo.description

        val url = BuildConfig.IMAGE_URL +
                mainInfo.icon +
                getString(R.string.icon_suffix)
        Glide
            .with(binding.root)
            .load(
                url
            )
            .dontAnimate()
            .centerInside()
            .into(binding.currentWeatherView.icon)
    }

    private fun subscribeForecastResult() {
        lifecycleScope.launch {
            viewModel.forecastState.observe(viewLifecycleOwner) { weatherState ->
                when (weatherState) {
                    is WeatherState.IsLoading -> {
                        //TODO show loading before loading data list
                    }
                    is WeatherState.ShowError -> {
                        //TODO show error instead of list
                    }
                    is WeatherState.ShowData -> {
                        adapter.submitList(weatherState.data.list)
                    }
                }
            }

        }

    }
}