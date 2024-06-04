package com.example.indoquest.ui.login

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
import com.example.indoquest.databinding.ActivityLoginBinding
import com.example.indoquest.databinding.ActivitySignUpBinding
import com.example.indoquest.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            supportActionBar?.hide();
        }
        displayAnimation()

        val edEmail = binding.edEmail
        val edPassword = binding.edPassword
        val loginBtn = binding.btnLogin
        val signUpTv = binding.tvSignup

        edEmail.validateInput('1')
        edPassword.validateInput('2')

        loginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        signUpTv.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("Recycle")
    fun displayAnimation(){
        val loginTitle = ObjectAnimator.ofFloat(binding.tvLoginTitle, View.ALPHA, 1f).setDuration(500)
        val loginLogo = ObjectAnimator.ofFloat(binding.ivLoginLogo, View.ALPHA, 1f).setDuration(500)
        val loginSubtitle = ObjectAnimator.ofFloat(binding.tvLoginSubtitle, View.ALPHA, 1f).setDuration(500)
        val edEmail = ObjectAnimator.ofFloat(binding.edEmail, View.ALPHA, 1f).setDuration(500)
        val edPassword = ObjectAnimator.ofFloat(binding.edPassword, View.ALPHA, 1f).setDuration(500)
        val cbRememberMe = ObjectAnimator.ofFloat(binding.cbRememberMe, View.ALPHA, 1f).setDuration(500)
        val tvforgotPassword = ObjectAnimator.ofFloat(binding.tvForgotPassword, View.ALPHA, 1f).setDuration(500)
        val loginBtn = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(500)
        val tvDontHaveAccount = ObjectAnimator.ofFloat(binding.tvDontHaveAccount, View.ALPHA, 1f).setDuration(500)
        val tvSignUp = ObjectAnimator.ofFloat(binding.tvSignup, View.ALPHA, 1f).setDuration(500)

        val together1= AnimatorSet().apply{
            playTogether(loginTitle, loginLogo, loginSubtitle)
        }

        val together2= AnimatorSet().apply{
            playTogether(cbRememberMe, tvforgotPassword)
        }

        val together3= AnimatorSet().apply{
            playTogether(loginBtn, tvDontHaveAccount, tvSignUp)
        }

        AnimatorSet().apply{
            playSequentially(together1, edEmail, edPassword, together2, together3)
            start()
        }
    }
}