package com.example.presentation.features.search.mappers

import com.example.domain.general.BaseMapper
import com.example.domain.twitter.models.Tweet
import com.example.presentation.features.search.models.PTweet

class PTweetMapper : BaseMapper<Tweet, PTweet>() {
    override fun transform(entity: Tweet): PTweet {
        return PTweet(
            createdAt = entity.createdAt,
            tweet = entity.tweet,
            user = entity.user
        )
    }
}