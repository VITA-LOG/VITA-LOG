package com.daineey.vita_log.data.remote

import com.daineey.vita_log.models.ConversationModel

interface ConversationRepository {
    suspend fun fetchConversations() : MutableList<ConversationModel>
    fun newConversation(conversation: ConversationModel) : ConversationModel
    suspend fun deleteConversation(conversationId: String)
}