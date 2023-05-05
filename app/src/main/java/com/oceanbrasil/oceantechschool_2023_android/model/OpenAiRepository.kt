package com.oceanbrasil.oceantechschool_2023_android.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OpenAiRepository {
    private const val OPENAI_API_KEY = "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"

    private val service: CompletionService

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer $OPENAI_API_KEY")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    init {
        // Initialize retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        // Create a service using retrofit
        service = retrofit.create(CompletionService::class.java)
    }

    // Create a function to make a request to the service
    fun postCompletion(prompt: String, callback: CompletionCallback) {
        // Create a CompletionRequest object
        val completionRequest = CompletionRequest(
            "text-davinci-003",
            prompt,
        )

        // Make a request to the service
        // Use the callback to return the answer
        val call = service.postCompletion(completionRequest)

        call.enqueue(object : retrofit2.Callback<CompletionResponse> {
            override fun onResponse(
                call: retrofit2.Call<CompletionResponse>,
                response: retrofit2.Response<CompletionResponse>
            ) {
                if (response.isSuccessful) {
                    val completionResponse = response.body()
                    val answer = completionResponse?.choices?.get(0)?.text
                    if (answer != null) {
                        callback.onSuccess(answer)
                    } else {
                        callback.onFailure()
                    }
                } else {
                    callback.onFailure()
                }
            }

            override fun onFailure(call: retrofit2.Call<CompletionResponse>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    interface CompletionCallback {
        fun onSuccess(answer: String)
        fun onFailure()
    }
}
