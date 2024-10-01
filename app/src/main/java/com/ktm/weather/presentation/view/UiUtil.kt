package com.ktm.weather.presentation.view

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.ktm.weather.R
import com.ktm.weather.presentation.util.Const
import java.util.Calendar
import java.util.TimeZone

object UiUtil {

    fun setWeatherIcon(context: Context, imageView: AppCompatImageView, weatherCode: Int) {
        if (weatherCode / 100 == 2) {
            Glide.with(context).load(R.drawable.ic_storm_weather).into(imageView)
        } else if (weatherCode / 100 == 3) {
            Glide.with(context).load(R.drawable.ic_rainy_weather).into(imageView)
        } else if (weatherCode / 100 == 5) {
            Glide.with(context).load(R.drawable.ic_rainy_weather).into(imageView)
        } else if (weatherCode / 100 == 6) {
            Glide.with(context).load(R.drawable.ic_snow_weather).into(imageView)
        } else if (weatherCode / 100 == 7) {
            Glide.with(context).load(R.drawable.ic_unknown).into(imageView)
        } else if (weatherCode == 800) {
            Glide.with(context).load(R.drawable.ic_clear_day).into(imageView)
        } else if (weatherCode == 801) {
            Glide.with(context).load(R.drawable.ic_few_clouds).into(imageView)
        } else if (weatherCode == 803) {
            Glide.with(context).load(R.drawable.ic_broken_clouds).into(imageView)
        } else if (weatherCode / 100 == 8) {
            Glide.with(context).load(R.drawable.ic_cloudy_weather).into(imageView)
        }
    }

    fun getWeatherStatus(weatherCode: Int): String {
        if (weatherCode / 100 == 2) {
            return Const.WEATHER_STATUS[0]
        } else if (weatherCode / 100 == 3) {
            return Const.WEATHER_STATUS[1]
        } else if (weatherCode / 100 == 5) {
            return Const.WEATHER_STATUS[2]
        } else if (weatherCode / 100 == 6) {
            return Const.WEATHER_STATUS[3]
        } else if (weatherCode / 100 == 7) {
            return Const.WEATHER_STATUS[4]
        } else if (weatherCode == 800) {
            return Const.WEATHER_STATUS[5]
        } else if (weatherCode == 801) {
            return Const.WEATHER_STATUS[6]
        } else if (weatherCode == 803) {
            return Const.WEATHER_STATUS[7]
        } else if (weatherCode / 100 == 8) {
            return Const.WEATHER_STATUS[8]
        }
        return Const.WEATHER_STATUS[4]
    }

    fun getWeatherAnimation(weatherCode: Int): Int {
        if (weatherCode / 100 == 2) {
            return R.raw.storm_weather
        } else if (weatherCode / 100 == 3) {
            return R.raw.rainy_weather
        } else if (weatherCode / 100 == 5) {
            return R.raw.rainy_weather
        } else if (weatherCode / 100 == 6) {
            return R.raw.snow_weather
        } else if (weatherCode / 100 == 7) {
            return R.raw.unknown
        } else if (weatherCode == 800) {
            return R.raw.clear_day
        } else if (weatherCode == 801) {
            return R.raw.few_clouds
        } else if (weatherCode == 803) {
            return R.raw.broken_clouds
        } else if (weatherCode / 100 == 8) {
            return R.raw.cloudy_weather
        }
        return R.raw.unknown
    }

    fun addDays(cal: Calendar, days: Int): Calendar {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = cal.timeInMillis
        calendar.add(Calendar.DATE, days)
        return calendar
    }

    fun getStartOfDayTimestamp(calendar: Calendar): Long {
        val newCalendar = Calendar.getInstance(TimeZone.getDefault())
        newCalendar.timeInMillis = calendar.timeInMillis
        newCalendar[Calendar.HOUR_OF_DAY] = 0
        newCalendar[Calendar.MINUTE] = 0
        newCalendar[Calendar.SECOND] = 0
        newCalendar[Calendar.MILLISECOND] = 0
        return newCalendar.timeInMillis
    }

    fun getEndOfDayTimestamp(calendar: Calendar): Long {
        val newCalendar = Calendar.getInstance(TimeZone.getDefault())
        newCalendar.timeInMillis = calendar.timeInMillis
        newCalendar[Calendar.HOUR_OF_DAY] = 23
        newCalendar[Calendar.MINUTE] = 59
        newCalendar[Calendar.SECOND] = 59
        newCalendar[Calendar.MILLISECOND] = 0
        return newCalendar.timeInMillis
    }
}