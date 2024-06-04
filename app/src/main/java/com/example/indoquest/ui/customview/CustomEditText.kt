package com.example.indoquest.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

class CustomEditText : AppCompatEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var isUsernameValid = false
    var isEmailValid = false
    var isPasswordValid = false
    var isConfirmPasswordValid = false

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    fun validateInput(inputType : Char){
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val inputText = s.toString().trim()
                when (inputType) {
                    '0' -> validateUsername(inputText)
                    '1' -> validateEmail(inputText)
                    '2' -> validatePassword(inputText)
                    '3' -> validateConfirmPassword(inputText)
                }
            }
        })
    }

    fun validateEmail(email : String){
        if(email.isEmpty()){
            isEmailValid = false
            error = "Email cannot be empty"
        }else{
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                isEmailValid = false
                error = "Email is not valid"
            }else{
                isEmailValid = true
            }
        }
    }


    fun validatePassword(password : String){
        if(password.isEmpty()){
            isPasswordValid = false
            error = "Password cannot be empty"
        }else{
            if(password.length < 8){
                error = "Password must be at least 8 characters"
                isPasswordValid = false
            }else{
                isPasswordValid = true
            }
        }
    }

    fun validateUsername(username : String){
        if(username.isEmpty()){
            isUsernameValid = false
            error = "Username cannot be empty"
        }else
            isUsernameValid = true
    }

    fun validateConfirmPassword(confirmPassword : String){
        if(confirmPassword.isEmpty()){
            isConfirmPasswordValid = false
            error = "Confirm password cannot be empty"
        }
    }
}