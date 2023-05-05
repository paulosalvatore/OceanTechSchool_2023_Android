package com.oceanbrasil.oceantechschool_2023_android.model

import com.google.gson.annotations.SerializedName
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
    @SerializedName("max_tokens")
    val maxTokens: Int,
)

data class CompletionResponse(
    val choices: List<CompletionChoice>,
)

data class CompletionChoice(
    val text: String,
)
