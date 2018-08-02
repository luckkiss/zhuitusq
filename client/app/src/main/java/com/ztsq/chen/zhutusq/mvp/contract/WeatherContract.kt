package com.ztsq.chen.zhutusq.mvp.contract

import com.ztsq.chen.zhutusq.base.BasePresenter
import com.ztsq.chen.zhutusq.base.BaseView
import com.ztsq.chen.zhutusq.mvp.model.bean.WeatherBean

interface WeatherContract {
    interface View : BaseView<Presenter> {
        fun setData(bean: WeatherBean)
    }

    interface Presenter : BasePresenter {
        fun requestData()
    }
}