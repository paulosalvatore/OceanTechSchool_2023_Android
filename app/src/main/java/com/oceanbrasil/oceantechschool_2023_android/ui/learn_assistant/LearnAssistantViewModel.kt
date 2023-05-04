package com.oceanbrasil.oceantechschool_2023_android.ui.learn_assistant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LearnAssistantViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Learn Assistant Fragment"
    }
    val text: LiveData<String> = _text
}
