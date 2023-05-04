package com.oceanbrasil.oceantechschool_2023_android.ui.learn_assistant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LearnAssistantViewModel : ViewModel() {
    private val _answer = MutableLiveData<String>().apply {
        value = "Test answer"
    }
    val answer: LiveData<String> = _answer

    fun ask(theme: String, question: String) {
        _answer.value = "Realizar uma pergunta para o tema escolhido: $theme; $question"
    }
}
