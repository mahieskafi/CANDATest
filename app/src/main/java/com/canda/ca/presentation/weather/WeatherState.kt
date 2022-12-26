package com.canda.ca.presentation.weather

sealed class WeatherState<out R> {
    class ShowData<out T>(val data: T) : WeatherState<T>()
    class ShowError(val message: String) : WeatherState<Nothing>()
    object IsLoading : WeatherState<Nothing>()
}
