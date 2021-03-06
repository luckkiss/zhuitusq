package com.ztsq.chen.zhutusq.network

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor(context: Context, baseUrl: String) {
    private var httpCacheDirectory: File? = null
    private var mContext: Context = context
    private var cache: Cache? = null
    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null
    private val DEFAULT_TIMEOUT: Long = 20
    private val url = baseUrl

    init {
        if (httpCacheDirectory == null) {
            httpCacheDirectory = File(mContext.cacheDir, "app_cache")
        }
        try {
            if (cache == null) {
                cache = Cache(httpCacheDirectory, 10 * 1024 * 1024)
            }
        } catch (e: Exception) {
            Log.e(this.javaClass.name, "Could not create http cache", e)
        }
        //okhttp创建了
        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .addInterceptor(CacheInterceptor(context))
                .addNetworkInterceptor(CacheInterceptor(context))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        //retrofit创建了
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        var gankInstance: RetrofitClient? = null
        @SuppressLint("StaticFieldLeak")
        @Volatile
        var weatherInstance: RetrofitClient? = null

        fun getGankInstance(context: Context, baseUrl: String): RetrofitClient {
            if (gankInstance == null) {
                synchronized(RetrofitClient::class) {
                    if (gankInstance == null) {
                        gankInstance = RetrofitClient(context, baseUrl)
                    }
                }
            }
            return gankInstance!!
        }

        fun getWeatherInstance(context: Context, baseUrl: String): RetrofitClient {
            if (weatherInstance == null) {
                synchronized(RetrofitClient::class) {
                    if (weatherInstance == null) {
                        weatherInstance = RetrofitClient(context, baseUrl)
                    }
                }
            }
            return weatherInstance!!
        }
    }

    fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return retrofit?.create(service)
    }
}