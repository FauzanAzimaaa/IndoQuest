package com.example.indoquest.ui.questshare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestShareViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Quest Share Fragment"
    }
    val text: LiveData<String> = _text
}