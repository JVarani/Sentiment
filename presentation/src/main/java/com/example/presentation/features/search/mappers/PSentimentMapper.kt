package com.example.presentation.features.search.mappers

import com.example.domain.general.BaseMapper
import com.example.domain.sentiment.enums.Sentiment
import com.example.presentation.features.search.enums.PSentiment

class PSentimentMapper : BaseMapper<Sentiment, PSentiment>() {
    override fun transform(entity: Sentiment): PSentiment {
        return when (entity) {
            Sentiment.HAPPY -> PSentiment.HAPPY
            Sentiment.NORMAL -> PSentiment.NORMAL
            Sentiment.SAD -> PSentiment.SAD
        }
    }
}