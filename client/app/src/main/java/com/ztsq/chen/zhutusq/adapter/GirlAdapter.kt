package com.ztsq.chen.zhutusq.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ztsq.chen.zhutusq.R
import com.ztsq.chen.zhutusq.mvp.model.bean.PickBean
import com.ztsq.chen.zhutusq.utils.ImageLoadUtils

class GirlAdapter internal constructor(context: Context, list: MutableList<PickBean.Result>?) : RecyclerView.Adapter<GirlAdapter.CommonHolder>() {
    private var mContext: Context? = null
    private var mList: MutableList<PickBean.Result>? = null
    private var inflater: LayoutInflater? = null

    init {
        mContext = context
        mList = list
        inflater = LayoutInflater.from(context)
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonHolder {
        return CommonHolder(inflater?.inflate(R.layout.item_girl, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CommonHolder, position: Int) {
        holder?.tvPicDes?.text = mList?.get(position)?.desc
        ImageLoadUtils.display(mContext, holder?.ivPic, mList?.get(position)?.url)
    }

    class CommonHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var ivPic: ImageView? = null
        var tvPicDes: TextView? = null

        init {
            ivPic = itemView?.findViewById(R.id.iv_pic)
            tvPicDes = itemView?.findViewById(R.id.tv_pic_des)
        }
    }
}