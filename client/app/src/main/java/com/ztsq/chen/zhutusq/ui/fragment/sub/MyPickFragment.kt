package com.ztsq.chen.zhutusq.ui.fragment.sub

import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.ztsq.chen.zhutusq.mvp.contract.PickContract
import com.ztsq.chen.zhutusq.ui.commom.PickBaseFragment
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.ztsq.chen.zhutusq.mvp.model.bean.PickBean

class MyPickFragment: PickBaseFragment(), PickContract.View, OnRefreshListener, OnLoadmoreListener {
    override fun onRefresh(refreshlayout: RefreshLayout?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutResources(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentVisibleChange(isVisible: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var mIsRefresh: Boolean = false
    private var mIsLoadMoreRefresh: Boolean = false

    override fun setData(bean: PickBean){

    }
}