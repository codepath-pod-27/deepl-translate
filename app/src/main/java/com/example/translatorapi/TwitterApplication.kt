package com.example.translatorapi

import android.app.Application
import android.content.Context
import com.codepath.oauth.OAuthBaseClient


class TwitterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        fun getRestClient(context: Context): TwitterClient {
            return OAuthBaseClient.getInstance(TwitterClient::class.java, context) as TwitterClient
        }
    }
}