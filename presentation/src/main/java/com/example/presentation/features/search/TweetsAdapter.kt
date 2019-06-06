package com.example.presentation.features.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.presentation.R
import com.example.presentation.features.search.models.PTweet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tweets.view.*

class TweetsAdapter(
    private val list: List<PTweet>
) : RecyclerView.Adapter<TweetsAdapter.ViewHolder>() {


    class ViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {

        fun bindView(pTweet: PTweet) {
            Picasso.get().load(pTweet.user.profileImage).into(item.imgItemTweet)
            item.txtItemTweetName.text = pTweet.user.name
            item.txtItemTweetTweets.text = pTweet.tweet
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_tweets, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}