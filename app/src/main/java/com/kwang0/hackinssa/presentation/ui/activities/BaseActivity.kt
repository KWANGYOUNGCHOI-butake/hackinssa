package com.kwang0.hackinssa.presentation.ui.activities

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.kwang0.hackinssa.App
import com.kwang0.hackinssa.helper.hideKeyboard


abstract class BaseActivity: AppCompatActivity() {

    private val TAG = BaseActivity::class.simpleName

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(App.localeHelper?.setLocale(newBase))
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v: View? = currentFocus
            if (v is EditText) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    Log.d("focus", "touchevent")
                    v.clearFocus()
                    this.hideKeyboard(v)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}