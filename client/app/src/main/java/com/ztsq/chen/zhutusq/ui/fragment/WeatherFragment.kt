package com.ztsq.chen.zhutusq.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.ztsq.chen.zhutusq.R
import com.ztsq.chen.zhutusq.adapter.WeatherAdapter
import com.ztsq.chen.zhutusq.mvp.contract.WeatherContract
import com.ztsq.chen.zhutusq.mvp.model.bean.WeatherBean
import com.ztsq.chen.zhutusq.mvp.presenter.WeatherPresenter
import com.ztsq.chen.zhutusq.ui.commom.CommonFragment
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment: CommonFragment(), WeatherContract.View, OnRefreshListener {
    private var mIsRefresh: Boolean = false
    lateinit private var mPresenter: WeatherPresenter
    lateinit private var mAdapter: WeatherAdapter

    override fun setData(bean: WeatherBean) {
        if (mIsRefresh) {
            mIsRefresh = false
            smartRefreshLayout.finishRefresh()
        }
        mAdapter = WeatherAdapter(context!!, bean)
        recyclerView.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_weather
    }

    override fun initView() {
        mPresenter = WeatherPresenter(context!!, this)
        recyclerView.layoutManager = LinearLayoutManager(context)

        smartRefreshLayout.setOnRefreshListener(this)
    }

    override fun loadData() {
        mPresenter.start()
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter.start()
        }
    }

}