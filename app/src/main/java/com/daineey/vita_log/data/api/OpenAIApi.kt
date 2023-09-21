package com.daineey.vita_log.data.api

import com.daineey.vita_log.constants.textCompletionsEndpoint
import com.daineey.vita_log.constants.textCompletionsTurboEndpoint
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface OpenAIApi {
    @POST(textCompletionsEndpoint)
    @Streaming
    fun textCompletionsWithStream(@Body body: JsonObject): Call<ResponseBody>

    @POST(textCompletionsTurboEndpoint)
    @Streaming
    fun textCompletionsTurboWithStream(@Body body: JsonObject): Call<ResponseBody>
}