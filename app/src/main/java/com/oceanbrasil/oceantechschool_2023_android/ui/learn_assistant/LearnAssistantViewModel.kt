package com.oceanbrasil.oceantechschool_2023_android.ui.learn_assistant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oceanbrasil.oceantechschool_2023_android.model.OpenAiRepository

class LearnAssistantViewModel : ViewModel() {
    private val _answer = MutableLiveData<String>()
    val answer: LiveData<String> = _answer

    fun ask(theme: String, question: String) {
        _answer.value = "Realizando a pergunta, aguarde..."

        val prompt = "Para o tema $theme, a resposta para a pergunta \"$question\" é:"

        OpenAiRepository.postCompletion(
            prompt,
            object : OpenAiRepository.CompletionCallback {
                override fun onSuccess(answer: String) {
                    _answer.postValue(answer)
                }

                override fun onFailure() {
                    _answer.postValue("Não foi possível obter uma resposta")
                }
            }
        )
    }
}
