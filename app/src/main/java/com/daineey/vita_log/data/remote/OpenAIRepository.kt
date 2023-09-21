package com.daineey.vita_log.data.remote

import com.daineey.vita_log.models.TextCompletionsParam
import kotlinx.coroutines.flow.Flow

interface OpenAIRepository {
    fun textCompletionsWithStream(params: TextCompletionsParam): Flow<String>
}