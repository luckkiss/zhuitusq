package com.ztsq.chen.zhutusq.mvp.contract

import com.ztsq.chen.zhutusq.base.BasePresenter
import com.ztsq.chen.zhutusq.base.BaseView
import com.ztsq.chen.zhutusq.mvp.model.bean.PickBean

interface PickContract {
    interface View : BaseView<Presenter> {
        fun setData(bean: PickBean)
    }

    interface Presenter : BasePresenter {
        fun requestData()
    }
}