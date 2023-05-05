package com.oceanbrasil.oceantechschool_2023_android.ui.learn_assistant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oceanbrasil.oceantechschool_2023_android.model.CompletionRequest
import com.oceanbrasil.oceantechschool_2023_android.model.OpenAiRepository

class LearnAssistantViewModel : ViewModel() {
    private val _answer = MutableLiveData<String>()
    val answer: LiveData<String> = _answer

    fun ask(theme: String, question: String) {
        _answer.value = "Realizando a pergunta, aguarde..."

        val maxResponseLength = 256

        val prompt = "Simule que você é um aplicativo de perguntas e respostas que recebe um" +
                "tema previamente selecionado de uma lista. A partir desse tema, você também" +
                "receberá uma pergunta que deverá ser respondida em até $maxResponseLength caracteres," +
                "sem utilizar nenhum símbolo de quebra de linha ou coisa do tipo, visto que a resposta " +
                "será exibida na íntegra para o usuário dentro do App.\n\n" +
                "Dito isso, seguem o tema e a pergunta:\n\n" +
                "Tema: $theme\n\n" +
                "Pergunta: $question\n\n" +
                "Resposta:"

        val completionRequest = CompletionRequest(
            "text-davinci-003",
            prompt,
            maxResponseLength,
        )

        OpenAiRepository.postCompletion(
            completionRequest,
            object : OpenAiRepository.CompletionCallback {
                override fun onSuccess(answer: String) {
                    _answer.postValue(answer)
                }

                override fun onFailure() {
                    _answer.postValue("Não foi possível obter uma resposta, tente novamente.")
                }
            }
        )
    }
}
