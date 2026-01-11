package com.example.myapplication.ui.no_internet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityNoInternetBinding
import com.example.myapplication.ui.splash.SplashActivity
import com.example.myapplication.utils.CheckInternet
import com.example.myapplication.utils.tap

class NoInternetActivity: BaseActivity<ActivityNoInternetBinding>(
    inflater = ActivityNoInternetBinding::inflate
){
    override fun onResume() {
        super.onResume()

    }

    override fun initView() {
        super.initView()

        binding.tvRetry.tap {
            if (!CheckInternet.isNetworkConnected(this)){
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
            }
        }
    }
}