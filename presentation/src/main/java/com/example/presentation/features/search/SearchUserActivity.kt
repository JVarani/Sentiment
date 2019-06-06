package com.example.presentation.features.search

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.inputmethod.EditorInfo
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.features.search.models.PTweet
import com.example.presentation.general.extensions.hideKeyboard
import kotlinx.android.synthetic.main.activity_search_user.*
import org.koin.android.architecture.ext.viewModel

class SearchUserActivity : BaseActivity<SearchUserViewModel>() {
    override val viewModel by viewModel<SearchUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)

        prepareViews()
        prepareObservers()

    }

    private fun prepareViews() {
        recyclerViewSearch.layoutManager = LinearLayoutManager(this)

        editTextSearch.apply {
            setOnEditorActionListener { text, action, _ ->
                if (action == EditorInfo.IME_ACTION_SEARCH && text.text.isNotBlank()) {
                    hideKeyboard()
                    viewModel.onSearchTweets(text.text.toString())
                }
                true
            }
        }
    }

    private fun prepareObservers() {
        viewModel
            .isLoading()
            .observe(this, Observer<Boolean> { isLoadingN ->
                isLoadingN?.let { isLoading ->
                    when {
                        isLoading -> {
                            progressSearchLoading.visibility = View.VISIBLE
                            recyclerViewSearch.visibility = View.INVISIBLE
                        }
                        else -> {
                            progressSearchLoading.visibility = View.GONE
                            imgSearchLogo.visibility = View.GONE
                            recyclerViewSearch.visibility = View.VISIBLE

                            editTextSearch.setCompoundDrawablesWithIntrinsicBounds(
                                getDrawable(R.drawable.ic_twitter_24dp),
                                null,
                                null,
                                null
                            )
                        }
                    }
                }
            })

        viewModel
            .getTweets()
            .observe(this, Observer<List<PTweet>> { listN ->
                listN?.let { list ->
                    recyclerViewSearch.adapter = TweetsAdapter(list)
                }
            })
    }
}