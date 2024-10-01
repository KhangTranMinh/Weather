package com.ktm.weather.presentation.ui.details

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorInt
import com.ktm.weather.R
import com.ktm.weather.databinding.ItemNextWeatherBinding
import com.ktm.weather.presentation.util.Const
import com.ktm.weather.presentation.view.UiUtil
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class NextWeatherUiModel(
    var dt: Long = 0,
    var temp: Double = 0.0,
    var minTemp: Double = 0.0,
    var maxTemp: Double = 0.0,
    var weatherId: Int = 0,
    var timestampStart: Long = 0,
    var timestampEnd: Long = 0,
    @ColorInt var color: Int = 0,
    @ColorInt var colorAlpha: Int = 0
) : AbstractItem<NextWeatherUiModel, NextWeatherUiModel.ViewHolder>() {

    override fun getType(): Int {
        return com.mikepenz.fastadapter.R.id.fastadapter_item_adapter
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_next_weather
    }

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    class ViewHolder internal constructor(
        view: View
    ) : FastAdapter.ViewHolder<NextWeatherUiModel>(view) {

        private var context: Context = view.context
        private var binding: ItemNextWeatherBinding = ItemNextWeatherBinding.bind(view)

        override fun bindView(item: NextWeatherUiModel, payloads: List<Any>) {
            binding.cardView.setCardBackgroundColor(item.color)
            val colors = intArrayOf(
                Color.TRANSPARENT,
                item.colorAlpha,
                Color.TRANSPARENT
            )
            val calendar = Calendar.getInstance(TimeZone.getDefault())
            calendar.timeInMillis = item.dt * 1000L
            binding.tvDay.text = Const.DAYS_OF_WEEK[calendar[Calendar.DAY_OF_WEEK] - 1]
            if (item.maxTemp < 0 && item.maxTemp > -0.5) {
                item.maxTemp = 0.0
            }
            if (item.minTemp < 0 && item.minTemp > -0.5) {
                item.minTemp = 0.0
            }
            if (item.temp < 0 && item.temp > -0.5) {
                item.temp = 0.0
            }
            binding.tvTemperature.text = String.format(
                Locale.getDefault(), "%.0f°", item.temp
            )
            binding.tvTemperatureMin.text = String.format(
                Locale.getDefault(), "%.0f°", item.minTemp
            )
            binding.tvTemperatureMax.text = String.format(
                Locale.getDefault(), "%.0f°", item.maxTemp
            )
            UiUtil.setWeatherIcon(context, binding.ivWeather, item.weatherId)
            val shape = GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors)
            shape.shape = GradientDrawable.OVAL
            binding.viewShadow.background = shape
        }

        override fun unbindView(item: NextWeatherUiModel) {
        }
    }
}