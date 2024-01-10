package com.dennnytech.paydash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.dennnytech.paydash.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySplashBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.onEvent(HomeEvent.Init)

        viewModel.remoteLoading.observe(this) {
            binding.clLoading.isVisible = it

            if (!it) {
//                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//                finish()
                goToMain()
            }
        }
    }

    private fun goToMain() {
        Handler().postDelayed(Runnable { //This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            // close this activity
            finish()
        }, 3000)
    }
}