package com.ztsq.chen.zhutusq.mvp.model

import android.content.Context
import com.ztsq.chen.zhutusq.mvp.model.bean.PickBean
import io.reactivex.Observable

abstract class PickBaseModel {
    protected val gankTypeAll:String="all"
    protected val gankTypeGirl:String="福利"

    abstract fun loadData(context: Context?, count: String?): Observable<PickBean>?
    abstract fun loadMoreData(context: Context, count:String?, page: String?): Observable<PickBean>?
}