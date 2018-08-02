package com.ztsq.chen.zhutusq.network

import com.ztsq.chen.zhutusq.mvp.model.bean.PickBean
import com.ztsq.chen.zhutusq.mvp.model.bean.WeatherBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        val Pick_BASE_URL: String
            get() = "http://gank.io/api/data/"
        val WEATHER_BASE_URL: String
            get() = "https://free-api.heweather.com/s6/"
    }

    //获取Gank第一页数据
    @GET("{type}/{count}/1")
    fun getPickData(@Path("type") type: String, @Path("count") count: String?): Observable<PickBean>?

    //获取Gank第一页之后的数据
    @GET("{type}/{count}/{page}")
    fun getPickMoreData(@Path("type") type: String, @Path("count") count: String?, @Path("page") page: String?): Observable<PickBean>

    @POST("weather")
    fun getWeatherData(@Query("key") key: String, @Query("location") location: String?): Observable<WeatherBean>?
}