package com.ztsq.chen.zhutusq.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object NetworkUtils {
    fun isNetConneted(context: Context):Boolean{
        val connectManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo : NetworkInfo?= connectManager.activeNetworkInfo
        return if(networkInfo==null){
            false
        }else{
            networkInfo.isAvailable&& networkInfo.isConnected
        }
    }
}