package com.zhuitu.chen.playpic.utils

import android.app.Activity
import android.content.Intent
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

inline fun <reified T : Activity?> Activity.switchActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
}

fun <T> Observable<T>.applySchedulers():Observable<T>{
    return subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}