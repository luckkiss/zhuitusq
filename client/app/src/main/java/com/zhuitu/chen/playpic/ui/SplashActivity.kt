package com.zhuitu.chen.playpic.ui

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.zhuitu.chen.playpic.R
import com.zhuitu.chen.playpic.ui.activity.MainActivity
import com.zhuitu.chen.playpic.utils.switchActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.ref.WeakReference

private val SWITCH_MAINACTIVITE = 1000 // 跳转主界面

class SplashActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        initView()
        MyHandler(this).sendEmptyMessageDelayed(SWITCH_MAINACTIVITE,1000)
    }

    private class MyHandler(activity: SplashActivity):Handler(){
        private val mActivity:WeakReference<SplashActivity> = WeakReference(activity);

        override fun handleMessage(msg: Message) {
            if(mActivity.get() == null){
                return
            }

            val activity = mActivity.get()
            when(msg.what){
                SWITCH_MAINACTIVITE->{
                    activity!!.switchActivity<MainActivity>()
                    activity.finish()
                }
                else->{

                }
            }
        }
    }

    private fun initView(){
        val font:Typeface = Typeface.createFromAsset(this.assets,"fonts/Lobster-1.4.otf")
        tv_splash_instruction.typeface = font
        tv_splash_instruction.text = "PickPicture"
    }

    override fun onDestroy() {
        MyHandler(this).removeCallbacksAndMessages(null);
        super.onDestroy()
    }
}