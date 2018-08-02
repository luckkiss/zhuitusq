package com.ztsq.chen.zhutusq.mvp.presenter

import android.content.Context
import com.ztsq.chen.zhutusq.mvp.contract.WeatherContract
import com.ztsq.chen.zhutusq.mvp.model.WeatherModel
import com.ztsq.chen.zhutusq.mvp.model.bean.WeatherBean
import com.ztsq.chen.zhutusq.utils.applySchedulers
import io.reactivex.Observable

class WeatherPresenter(context: Context, view: WeatherContract.View) : WeatherContract.Presenter {

    private var mContext: Context? = null
    private var mView: WeatherContract.View? = null
    private val mModel: WeatherModel by lazy {
        WeatherModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable: Observable<WeatherBean>? = mContext?.let { mModel.loadData(it,"上海") }
        observable?.applySchedulers()?.subscribe { commonBean: WeatherBean -> mView?.setData(commonBean) }
    }
}