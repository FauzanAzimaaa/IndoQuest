package com.example.indoquest.ui.onboarding

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import com.example.indoquest.MainActivity
import com.example.indoquest.R
import com.example.indoquest.databinding.ActivityOnBoardingBinding
import com.example.indoquest.ui.login.LoginActivity
import com.example.indoquest.ui.onboarding.adapter.OnBoardingViewPagerAdapter
import com.example.indoquest.ui.onboarding.model.OnBoardingData
import com.google.android.material.tabs.TabLayout

class OnBoardingActivity : AppCompatActivity() {

    var onBoardingViewPagerAdapter : OnBoardingViewPagerAdapter? = null
    var tabLayout : TabLayout? = null
    var onBoardingViewPager : ViewPager? = null
    var nextBtn : Button? = null
    var position = 0
    var sharedPreferences : SharedPreferences? = null

    companion object{
        val IS_FIRST_TIME_RUN = "IsFirstTimeRun"
        val ONBOARDING_PREF = "ON-BOARDING_PREF"
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        if(restorePrefData()){
//            val intent = Intent(applicationContext, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()

        setContentView(R.layout.activity_on_boarding)

        tabLayout = findViewById(R.id.tab_indicator)
        nextBtn = findViewById(R.id.next_btn)

        val onBoardingData : MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Explorasi Keindahan Bali Bersama Kami",
            "Explorasi Keindahan Bali Bersama Kami", R.drawable.bg_onboarding1))
        onBoardingData.add(OnBoardingData("Explorasi Keindahan Bali Bersama Kami",
            "Explorasi Keindahan Bali Bersama Kami", R.drawable.bg_onboarding2))
        onBoardingData.add(OnBoardingData("Explorasi Keindahan Bali Bersama Kami",
            "Explorasi Keindahan Bali Bersama Kami", R.drawable.bg_onboarding3))

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem
        nextBtn?.setOnClickListener {
            if(position < onBoardingData.size){
                position++
                onBoardingViewPager!!.currentItem = position
            }
            if(position == onBoardingData.size){
                savePrefData()
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
                if(tab.position == onBoardingData.size - 1){
                    nextBtn!!.text = "get started"
                }else{
                    nextBtn!!.text = "next"
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun savePrefData(){
        sharedPreferences = applicationContext.getSharedPreferences(ONBOARDING_PREF, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences!!.edit()
        editor.putBoolean(IS_FIRST_TIME_RUN, true)
        editor.apply()
    }

    private fun restorePrefData() : Boolean{
        sharedPreferences = applicationContext.getSharedPreferences(ONBOARDING_PREF, Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean(IS_FIRST_TIME_RUN, false)
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>){
        onBoardingViewPager = findViewById(R.id.screenPager)
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)
    }
}