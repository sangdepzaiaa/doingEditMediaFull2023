package com.example.myapplication.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import com.example.myapplication.R

abstract class BaseDialog<VB: ViewBinding>(
    context: Context,
    var inflater: (LayoutInflater) -> VB
): Dialog(context, R.style.full_screen_dialog){
    protected val binding by lazy { inflater(layoutInflater) }

    init {
        setContentView(binding.root)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setOnKeyListener { _,onKey,onEvent ->
            if (onKey == KeyEvent.KEYCODE_BACK && onEvent.action == KeyEvent.ACTION_UP) true
            else false
        }
        initView()
        bindView()
    }

//    override fun onStart() {
//        super.onStart()
//
//        val width = (context.resources.displayMetrics.widthPixels * 0.4).toInt()
//        val window = window ?: return
//        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
//        window.setDimAmount(0.0f)
//
//        val params = window.attributes
//        params.gravity = Gravity.END
//        // Nếu muốn cách mép phải một chút cho đẹp thì thêm margin:
//        params.x = 20 // Khoảng cách so với mép phải (pixel)
//        window.attributes = params
//
//    }

    open fun initView(){}
    open fun bindView(){}
}