package com.example.presentation.features.search.models

import com.example.domain.twitter.models.User

data class PTweet(
    val createdAt: String,
    val tweet: String,
    val user: User
)