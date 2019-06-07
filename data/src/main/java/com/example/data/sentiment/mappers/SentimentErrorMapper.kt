package com.example.data.sentiment.mappers

import com.example.domain.general.BaseMapper

class SentimentErrorMapper : BaseMapper<Throwable, Throwable>() {

    override fun transform(entity: Throwable): Throwable {
        //Add here specific errors if needed
        return entity
    }
}