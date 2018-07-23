package com.zhuitu.chen.playpic.ui.activity

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import com.gyf.barlibrary.ImmersionBar
import com.zhuitu.chen.playpic.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view:View = findViewById<View>(R.id.view_status) as View
        ImmersionBar.with(this).statusBarView(view).init()

        /*设置ActionBar*/
        setActionBar()
        setDrawerToggle()
        initToolBar()
    }

    private fun setActionBar(){
        toolbar.title=""
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setDrawerToggle() {
        val mToggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(mToggle)
        /*同步DrawerLayout的状态*/
        mToggle.syncState()
    }

    /*设置ToolBar标题*/
    private fun initToolBar() {
        tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        tv_bar_title.text = "追图神器"
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy()//不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}
