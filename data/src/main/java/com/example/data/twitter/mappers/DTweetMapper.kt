package com.example.data.twitter.mappers

import com.example.data.twitter.models.DTweet
import com.example.data.twitter.models.DUser
import com.example.domain.general.BaseMapper
import com.example.domain.twitter.models.Tweet
import com.example.domain.twitter.models.User

class DTweetMapper : BaseMapper<DTweet, Tweet>() {
    override fun transform(entity: DTweet): Tweet {
        return Tweet(
            tweet = entity.tweet ?: "",
            user = mapUser(entity.user)
        )
    }

    private fun mapUser(dUser: DUser?) = User(
        name = dUser?.name ?: "",
        profileImage = dUser?.profileImage ?: ""
    )
}