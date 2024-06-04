package com.example.indoquest.ui.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.indoquest.MainActivity
import com.example.indoquest.R
import com.example.indoquest.databinding.ActivityMainBinding
import com.example.indoquest.databinding.ActivitySignUpBinding
import com.example.indoquest.ui.login.LoginActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar?.hide();
        }
        displayAnimation()

        val edUsername = binding.edUsername
        val edEmail = binding.edEmail
        val edPassword = binding.edPassword
        val signUpBtn = binding.btnSignup
        val loginTv = binding.tvLogin

        edUsername.validateInput('0')
        edEmail.validateInput('1')
        edPassword.validateInput('2')

        signUpBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        loginTv.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("Recycle")
    fun displayAnimation(){
        val signUpTitle = ObjectAnimator.ofFloat(binding.tvSignupTitle, View.ALPHA, 1f).setDuration(500)
        val signUpLogo = ObjectAnimator.ofFloat(binding.ivSignupLogo, View.ALPHA, 1f).setDuration(500)
        val signUpSubtitle = ObjectAnimator.ofFloat(binding.tvSignupSubtitle, View.ALPHA, 1f).setDuration(500)
        val edUsername = ObjectAnimator.ofFloat(binding.edUsername, View.ALPHA, 1f).setDuration(500)
        val edEmail = ObjectAnimator.ofFloat(binding.edEmail, View.ALPHA, 1f).setDuration(500)
        val edPassword = ObjectAnimator.ofFloat(binding.edPassword, View.ALPHA, 1f).setDuration(500)
        val edConfirmPassword = ObjectAnimator.ofFloat(binding.edConfirmPassword, View.ALPHA, 1f).setDuration(500)
        val signUpBtn = ObjectAnimator.ofFloat(binding.btnSignup, View.ALPHA, 1f).setDuration(500)
        val tvHaveAccount = ObjectAnimator.ofFloat(binding.tvHaveAccount, View.ALPHA, 1f).setDuration(500)
        val tvLogin = ObjectAnimator.ofFloat(binding.tvLogin, View.ALPHA, 1f).setDuration(500)

        val together1= AnimatorSet().apply{
            playTogether(signUpTitle, signUpLogo, signUpSubtitle)
        }

        val together2= AnimatorSet().apply{
            playTogether(signUpBtn, tvHaveAccount, tvLogin)
        }

        AnimatorSet().apply{
            playSequentially(together1, edUsername, edEmail, edPassword, edConfirmPassword, together2)
            start()
        }
    }
}