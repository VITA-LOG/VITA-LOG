package com.daineey.vita_log.di

import com.daineey.vita_log.data.remote.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun conversationRepository(
        repo: ConversationRepositoryImpl
    ): ConversationRepository

    @Binds
    abstract fun messageRepository(
        repo: MessageRepositoryImpl
    ): MessageRepository

    @Binds
    abstract fun openAIRepository(
        repo: OpenAIRepositoryImpl
    ): OpenAIRepository
}
