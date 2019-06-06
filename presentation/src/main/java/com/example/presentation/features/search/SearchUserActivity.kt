package com.example.presentation.features.search

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import com.example.presentation.R
import com.example.presentation.base.BaseActivity
import com.example.presentation.features.search.models.PTweet
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

        editTextSearch.setOnEditorActionListener { text, action, _ ->
            if (action == EditorInfo.IME_ACTION_SEARCH && text.text.isNotBlank()) {
                viewModel.onSearchTweets(text.text.toString())
            }
            true
        }
    }

    private fun prepareObservers() {
        viewModel
            .getTweets()
            .observe(this, Observer<List<PTweet>> { listN ->
                listN?.let { list ->
                    recyclerViewSearch.adapter = TweetsAdapter(list)
                }
            })
    }
}