package com.ztsq.chen.zhutusq.mvp.model.bean

class PickBean(
        val error: Boolean, //false
        val results: ArrayList<Result>){
    data class Result(
            val _id: String,
            val desc: String,
            val url: String
    )
}