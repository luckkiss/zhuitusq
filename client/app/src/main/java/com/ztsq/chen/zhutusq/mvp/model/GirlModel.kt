package com.ztsq.chen.zhutusq.mvp.model

import android.content.Context
import com.ztsq.chen.zhutusq.mvp.model.bean.PickBean
import com.ztsq.chen.zhutusq.network.ApiService
import com.ztsq.chen.zhutusq.network.RetrofitClient
import io.reactivex.Observable

class GirlModel :PickBaseModel(){
    private fun getApiService(context: Context?): ApiService? {
        val retrofitClient = context?.let { RetrofitClient.getGankInstance(it, ApiService.Gank_BASE_URL) }
        return retrofitClient?.create(ApiService::class.java)
    }

    override fun loadData(context: Context?, count: String?): Observable<PickBean>? {
        return getApiService(context)?.getGankData(gankTypeGirl,count)
    }

    override fun loadMoreData(context: Context, count: String?, page: String?): Observable<PickBean>? {
        return getApiService(context)?.getGankMoreData(gankTypeGirl,count, page)
    }
}