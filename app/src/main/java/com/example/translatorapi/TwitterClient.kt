package com.example.translatorapi

import android.content.Context
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.codepath.oauth.OAuthBaseClient
import com.github.scribejava.apis.TwitterApi



class TwitterClient(context: Context) : OAuthBaseClient(
    context, REST_API_INSTANCE, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET,
    null, String.format(
        REST_CALLBACK_URL_TEMPLATE,
        context.getString(R.string.intent_host),
        context.getString(R.string.intent_scheme),
        context.packageName,
        FALLBACK_URL
    )
) {

    companion object {
        val REST_API_INSTANCE = TwitterApi.instance()

        const val REST_URL = "https://api.twitter.com/1.1"

        const val REST_CONSUMER_KEY =
            "4KxocRp2Wh8RZ9cy1KJEjxGVy"// Change this inside apikey.properties

        const val REST_CONSUMER_SECRET =
            "EeyJ4vEZN3al7c0C13bMwAY3pGc2RASrampYtvJvnX1kLDHKJf"// Change this inside apikey.properties

        // Landing page to indicate the OAuth flow worked in case Chrome for Android 25+ blocks navigation back to the app.
        const val FALLBACK_URL =
            "https://codepath.github.io/android-rest-client-template/success.html"

        // See https://developer.chrome.com/multidevice/android/intents
        const val REST_CALLBACK_URL_TEMPLATE =
            "intent://%s#Intent;action=android.intent.action.VIEW;scheme=%s;package=%s;S.browser_fallback_url=%s;end"
    }

    // CHANGE THIS
    // DEFINE METHODS for different API endpoints here
    fun getHomeTimeline(handler: JsonHttpResponseHandler) {
        val apiUrl =
            getApiUrl("statuses/home_timeline.json")

        // Can specify query string params directly or through RequestParams.
        val params = RequestParams()
        params.put("count", "20")
        params.put("since_id", 1)
        client.get(apiUrl, params, handler)
    }

    fun publishTweet(tweetContent: String, handler: JsonHttpResponseHandler) {
        val apiUrl =
            getApiUrl("statuses/update.json")

        // Can specify query string params directly or through RequestParams.
        val params = RequestParams()
        params.put("status", tweetContent)
        client.post(apiUrl, params, "", handler)
    }

    fun getNextPageOfTweets(handler: JsonHttpResponseHandler, maxId: Long) {
        val apiUrl = getApiUrl("statuses/home_timeline.json")
        val params = RequestParams()
        params.put("count", "25")
        params.put("max_id", maxId)
        client.get(apiUrl, params, handler)
    }



    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
	 * 	  i.e getApiUrl("statuses/home_timeline.json")
	 * 2. Define the parameters to pass to the request (query or body)
	 *    i.e val params = RequestParams("foo", "bar")
	 * 3. Define the request method and make a call to the client
	 *    i.e client.get(apiUrl, params, handler)
	 *    i.e client.post(apiUrl, params, handler)
	 */
}