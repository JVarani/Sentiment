package com.example.data.twitter.models

import com.google.gson.annotations.SerializedName

data class DUser(
    @SerializedName("name") val name: String?,
    @SerializedName("screen_name") val screenName: String?,
    @SerializedName("profile_image_url_https") val profileImage: String?
)