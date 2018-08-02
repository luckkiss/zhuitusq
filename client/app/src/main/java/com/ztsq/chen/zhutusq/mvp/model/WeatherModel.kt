package com.ztsq.chen.zhutusq.mvp.model

import android.content.Context
import com.ztsq.chen.zhutusq.mvp.model.bean.WeatherBean
import com.ztsq.chen.zhutusq.network.ApiService
import com.ztsq.chen.zhutusq.network.RetrofitClient
import io.reactivex.Observable

class WeatherModel : WeatherBaseModel(){

    override fun loadData(context: Context?, location: String?): Observable<WeatherBean>? {
        return getApiService(context)?.getWeatherData(weatherKey,location)
    }

//    override fun loadMoreData(context: Context, location: String?, page: String?): Observable<GankBean>? {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

    private fun getApiService(context: Context?): ApiService? {
        val retrofitClient = context?.let { RetrofitClient.getWeatherInstance(it, ApiService.WEATHER_BASE_URL) }
        return retrofitClient?.create(ApiService::class.java)
    }
}