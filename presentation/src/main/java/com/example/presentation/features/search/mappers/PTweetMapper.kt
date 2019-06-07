package com.example.presentation.features.search.mappers

import com.example.domain.general.BaseMapper
import com.example.domain.twitter.models.Tweet
import com.example.domain.twitter.models.User
import com.example.presentation.features.search.models.PTweet
import com.example.presentation.features.search.models.PUser

class PTweetMapper : BaseMapper<Tweet, PTweet>() {
    override fun transform(entity: Tweet): PTweet {
        return PTweet(
            tweet = entity.tweet,
            user = mapUser(entity.user)
        )
    }

    private fun mapUser(user: User?) = PUser(
        name = user?.name ?: "",
        profileImage = user?.profileImage ?: ""
    )
}