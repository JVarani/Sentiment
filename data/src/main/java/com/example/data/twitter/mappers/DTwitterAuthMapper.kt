package com.example.data.twitter.mappers

import com.example.data.twitter.models.DTwitterAuth
import com.example.domain.general.BaseMapper
import com.example.domain.twitter.models.TwitterAuth

class DTwitterAuthMapper : BaseMapper<DTwitterAuth, TwitterAuth>() {
    override fun transform(entity: DTwitterAuth): TwitterAuth {
        return TwitterAuth(
            accessToken = entity.accessToken ?: ""
        )
    }
}