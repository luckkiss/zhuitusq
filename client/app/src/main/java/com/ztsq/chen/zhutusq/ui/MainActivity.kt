package com.ztsq.chen.zhutusq.ui

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.gyf.barlibrary.ImmersionBar
import com.ztsq.chen.zhutusq.R
import com.ztsq.chen.zhutusq.ui.fragment.MainFragment
import com.ztsq.chen.zhutusq.ui.fragment.WeatherFragment
import com.ztsq.chen.zhutusq.utils.switchActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mainFragment: MainFragment
    private lateinit var weatherFragment: WeatherFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 沉浸式
        val view: View = findViewById<View>(R.id.view_status) as View
        ImmersionBar.with(this).statusBarView(view).init()

        setActionBar()
        setDrawerToggle()
        setListener()
        initToolBar()

        initFragment(savedInstanceState)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            //异常情况
            val mFragments: List<Fragment> = supportFragmentManager.fragments
            for (item in mFragments) {
                if (item is MainFragment) {
                    mainFragment = item
                }
                if (item is WeatherFragment) {
                    weatherFragment = item
                }
            }
        }else{
            mainFragment = MainFragment()
            weatherFragment = WeatherFragment()
            val fragmentTrans = supportFragmentManager.beginTransaction()
            fragmentTrans.add(R.id.fl_content, mainFragment)
            fragmentTrans.add(R.id.fl_content, weatherFragment)
            fragmentTrans.commit()
        }

        supportFragmentManager.beginTransaction().show(mainFragment)
                .hide(weatherFragment)
                .commit()
    }

    /*设置ActionBar*/
    private fun setActionBar(){
        toolbar.title=""
        setSupportActionBar(toolbar)
    }

    /*设置DrawerLayout的开关,并且和Home图标联动*/
    private fun setDrawerToggle() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, 0, 0)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun setListener() {
//        nav_view.setNavigationItemSelectedListener(this)

        nav_view.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_manage -> {
                    tv_bar_title.text = "PickPicture"
                    supportFragmentManager.beginTransaction().show(mainFragment)
                            .hide(weatherFragment)
                            .commit()
                }
                R.id.nav_item_weather -> {
                    tv_bar_title.text = "天气"
                    supportFragmentManager.beginTransaction().show(weatherFragment)
                            .hide(mainFragment)
                            .commit()
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun initToolBar(){
        tv_bar_title.typeface = Typeface.createFromAsset(this.assets, "fonts/Lobster-1.4.otf")
        tv_bar_title.text = resources.getString(R.string.main_activity_item_all)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        return true
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_other) {
            //显示右侧栏
            if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            drawer_layout.openDrawer(GravityCompat.END)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy()
    }
}
