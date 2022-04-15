package com.example.translatorapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast




import android.content.Intent


import com.twitter.sdk.android.core.identity.TwitterLoginButton


import android.view.View
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.Twitter.initialize
import kotlin.Result
import com.twitter.sdk.android.core.TwitterSession

import com.twitter.sdk.android.core.TwitterAuthConfig

import com.twitter.sdk.android.core.DefaultLogger

import com.twitter.sdk.android.core.TwitterConfig








class LoginActivity : AppCompatActivity() {
    lateinit var loginButton: TwitterLoginButton
    override fun onCreate(savedInstanceState: Bundle?) {
        val config = TwitterConfig.Builder(this)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(
                TwitterAuthConfig(
                    getString(R.string.twitter_consumer_key),
                    getString(R.string.twitter_consumer_secret)
                )
            )
            .debug(true)
            .build()
        initialize(config)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       initialize(this);

        loginButton = findViewById<TwitterLoginButton>(R.id.login_button)
        loginButton.callback = object :
            Callback<TwitterSession?>() {


            override fun success(result: com.twitter.sdk.android.core.Result<TwitterSession?>?) {
                val session = TwitterCore.getInstance().sessionManager.activeSession
                val authToken = session.authToken
                loginMethod(session)
            }

            override fun failure(exception: TwitterException?) {
                Toast.makeText(getApplicationContext(),"Login fail",Toast.LENGTH_LONG).show();
            }
        }


    }

    fun loginMethod(twitterSession: TwitterSession) {
        val userName = twitterSession.userName
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("username", userName)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data)
    }




}