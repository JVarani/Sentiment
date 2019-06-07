package com.example.data.sentiment.mappers

import com.example.data.sentiment.ro.DSentimentAnalyzerResponseObject
import com.example.domain.general.BaseMapper
import com.example.domain.sentiment.models.SentimentScore

class DSentimentAnalyzerResponseObjectMapper :
    BaseMapper<DSentimentAnalyzerResponseObject, SentimentScore>() {
    override fun transform(entity: DSentimentAnalyzerResponseObject): SentimentScore {
        return SentimentScore(
            score = entity.dSentimentScore.score
        )
    }
}