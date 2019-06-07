package com.example.presentation

import com.example.domain.sentiment.SentimentUseCase
import com.example.domain.twitter.TwitterUseCase
import com.example.domain.twitter.models.Tweet
import com.example.domain.twitter.models.User
import com.example.presentation.features.search.SearchUserViewModel
import com.example.presentation.features.search.mappers.PSentimentMapper
import com.example.presentation.features.search.mappers.PTweetMapper
import com.example.presentation.features.search.models.PTweet
import com.example.presentation.features.search.models.PUser
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SearchUserViewModelTest {

    //TODO Fix and create tests

    @Mock
    private lateinit var pSentimentMapper: PSentimentMapper

    @Mock
    private lateinit var pTweetMapper: PTweetMapper

    @Mock
    private lateinit var useCaseSentiment: SentimentUseCase

    @Mock
    private lateinit var useCaseTwitter: TwitterUseCase

    private val testScheduler = TestScheduler()

    private val viewModel by lazy {
        SearchUserViewModel(
            pSentimentMapper = pSentimentMapper,
            pTweetMapper = pTweetMapper,
            useCaseSentiment = useCaseSentiment,
            useCaseTwitter = useCaseTwitter
        )
    }

    @Test
    fun gIVEN_fake_name_WHEN_get_tweets_by_user_THEN_return_success() {
        // Given
        whenever(
            useCaseTwitter.searchTweets(any())
        ) doReturn Single.just(FAKE_TWEET_LIST).subscribeOn(testScheduler)

        whenever(
            pTweetMapper.transform(FAKE_TWEET_LIST)
        ) doReturn FAKE_PTWEET_LIST

        // When
        viewModel.onSearchTweets(FAKE_NAME)

        // Then
        testScheduler.triggerActions()

        assertEquals(true, viewModel.isLoading().value)
        testScheduler.triggerActions()

        assertEquals(FAKE_PTWEET_LIST, viewModel.getTweets().value)
        assertEquals(false, viewModel.isLoading().value)
    }

    companion object {
        private const val FAKE_NAME = "Teste"

        private val FAKE_P_USER = PUser(
            name = "",
            profileImage = ""
        )

        private val FAKE_PTWEET_LIST = listOf(
            PTweet(
                tweet = "",
                user = FAKE_P_USER
            ),
            PTweet(
                tweet = "",
                user = FAKE_P_USER
            )
        )

        private val FAKE_USER = User(
            name = "",
            profileImage = ""
        )

        private val FAKE_TWEET_LIST = listOf(
            Tweet(
                tweet = "",
                user = FAKE_USER
            ),
            Tweet(
                tweet = "",
                user = FAKE_USER
            )
        )
    }
}