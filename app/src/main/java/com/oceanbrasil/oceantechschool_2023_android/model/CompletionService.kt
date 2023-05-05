package com.oceanbrasil.oceantechschool_2023_android.model

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CompletionService {
    @POST("completions")
    fun postCompletion(@Body completionRequest: CompletionRequest): Call<CompletionResponse>
}

data class CompletionRequest(
    val model: String,
    val prompt: String,
)

data class CompletionResponse(
    val choices: List<CompletionChoice>,
)

data class CompletionChoice(
    val text: String,
)
