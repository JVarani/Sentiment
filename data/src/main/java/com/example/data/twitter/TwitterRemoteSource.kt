package com.example.data.twitter

import com.example.data.twitter.models.DTweet
import com.example.data.twitter.models.DTwitterAuth
import io.reactivex.Single
import retrofit2.http.*

interface TwitterRemoteSource {

    @GET("1.1/statuses/user_timeline.json")
    fun searchTweets(
        @Header("authorization") auth: String,
        @Query("screen_name") name: String
    ): Single<List<DTweet>>

    @FormUrlEncoded
    @POST("oauth2/token")
    fun getAuthToken(
        @Header("authorization") auth: String,
        @Field("grant_type") grantType: String = "client_credentials"
    ): Single<DTwitterAuth>
}