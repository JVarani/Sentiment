package com.example.domain.twitter.models

data class Tweet(
    val createdAt: String,
    val tweet: String,
    val user: User
)