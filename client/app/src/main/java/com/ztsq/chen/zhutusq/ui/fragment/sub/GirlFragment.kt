package com.ztsq.chen.zhutusq.ui.fragment.sub

import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.ztsq.chen.zhutusq.R
import com.ztsq.chen.zhutusq.adapter.GirlAdapter
import com.ztsq.chen.zhutusq.mvp.Presenter.GirlPresenter
import com.ztsq.chen.zhutusq.mvp.contract.PickContract
import com.ztsq.chen.zhutusq.mvp.model.bean.PickBean
import com.ztsq.chen.zhutusq.ui.commom.PickBaseFragment
import kotlinx.android.synthetic.main.fragment_pick_item.*
import java.io.Console

class GirlFragment : PickBaseFragment() , PickContract.View , OnRefreshListener, OnLoadmoreListener {

    private var mIsRefresh: Boolean = false
    private var mIsLoadMoreRefresh: Boolean = false
    private lateinit var mPresenter: GirlPresenter
    private lateinit var mAdapter: GirlAdapter
    private var mList = ArrayList<PickBean.Result>()
    private val mCount: String? = "10"
    private var mPage: Int? = 1

    override fun setData(bean: PickBean) {
        if (mIsRefresh) {
            mIsRefresh = false
            smartRefreshLayout.finishRefresh()
            if (mList.size > 0) {
                mPage = 1
                mList.clear()
            }
        }
        if (mIsLoadMoreRefresh) {
            mIsLoadMoreRefresh = false
            smartRefreshLayout.finishLoadmore()
        }
        bean.results.forEach {
            mList.add(it)
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun onFragmentVisibleChange(isVisible: Boolean) {
        if (isVisible) {
            if (mList.size == 0)
                mPresenter.start()
        }
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_pick_item
    }

    override fun initView() {
        Log.i("GirlFragment","---------------->>")
        mPresenter = GirlPresenter(context!!, this)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        mAdapter = GirlAdapter(context!!, mList)
        recyclerView.adapter = mAdapter

        smartRefreshLayout.setOnRefreshListener(this)
        smartRefreshLayout.setOnLoadmoreListener(this)
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        if (!mIsRefresh) {
            mIsRefresh = true
            mPresenter.start()
        }
    }

    override fun onLoadmore(refreshLayout: RefreshLayout?) {
        if (!mIsLoadMoreRefresh) {
            mIsLoadMoreRefresh = true
            mPage = mPage!! + 1
            mPresenter.moreData(mCount, mPage.toString())
        }
    }
}