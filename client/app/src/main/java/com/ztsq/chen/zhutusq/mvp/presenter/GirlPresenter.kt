package com.ztsq.chen.zhutusq.mvp.Presenter

import android.content.Context
import com.ztsq.chen.zhutusq.mvp.contract.PickContract
import com.ztsq.chen.zhutusq.mvp.model.GirlModel
import com.ztsq.chen.zhutusq.mvp.model.bean.PickBean
import com.ztsq.chen.zhutusq.utils.applySchedulers
import io.reactivex.Observable

class GirlPresenter(context: Context, view: PickContract.View) : PickContract.Presenter {
    private var mContext: Context? = null
    private var mView: PickContract.View? = null
    private val mModel: GirlModel by lazy {
        GirlModel()
    }

    init {
        mContext = context
        mView = view
    }

    override fun start() {
        requestData()
    }

    override fun requestData() {
        val observable: Observable<PickBean>? = mContext?.let { mModel.loadData(it, "20") }
        observable?.applySchedulers()?.subscribe { commonBean: PickBean -> mView?.setData(commonBean) }
    }

    fun moreData(count: String?, page: String?) {
        val observable: Observable<PickBean>? = mContext?.let { mModel.loadMoreData(it, count, page) }
        observable?.applySchedulers()?.subscribe { commonBean: PickBean -> mView?.setData(commonBean) }
    }
}