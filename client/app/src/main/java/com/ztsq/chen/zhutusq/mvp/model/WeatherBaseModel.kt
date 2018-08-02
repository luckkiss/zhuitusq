package com.ztsq.chen.zhutusq.mvp.model

import android.content.Context
import com.ztsq.chen.zhutusq.mvp.model.bean.WeatherBean
import io.reactivex.Observable

abstract class WeatherBaseModel {
    protected val weatherKey:String="573d1ed0b83f4e978535b7c05fa503e0"

    abstract fun loadData(context: Context?, location: String?): Observable<WeatherBean>?
}