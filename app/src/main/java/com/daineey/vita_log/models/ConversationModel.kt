package com.daineey.vita_log.models

import java.util.*

data class ConversationModel(
    var id: String = Date().time.toString(),
    var title: String = "",
    var createdAt: Date = Date(),
)