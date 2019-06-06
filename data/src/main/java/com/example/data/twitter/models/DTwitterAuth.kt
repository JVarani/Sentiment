package com.example.data.twitter.models

import com.google.gson.annotations.SerializedName

data class DTwitterAuth(
    @SerializedName("access_token")
    val accessToken: String?
) 