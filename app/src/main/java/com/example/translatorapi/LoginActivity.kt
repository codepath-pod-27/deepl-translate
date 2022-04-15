package com.example.translatorapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.parse.ParseUser

import com.parse.twitter.ParseTwitterUtils

import com.parse.LogInCallback
import com.parse.ParseException
import com.parse.SaveCallback
import android.content.Intent

import android.content.DialogInterface
import android.app.AlertDialog
import android.app.ProgressDialog;
import com.parse.ParseUser.logInWithInBackground
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

//        ParseTwitterUtils.logIn(this@LoginActivity) { user, err ->
//            if (err != null) {
//
//                ParseUser.logOut()
//                Log.e("err", "err", err)
//            }
//            if (user == null) {
//
//                ParseUser.logOut()
//                Toast.makeText(
//                    this@LoginActivity,
//                    "The user cancelled the Twitter login.",
//                    Toast.LENGTH_LONG
//                ).show()
//                Log.d("MyApp", "Uh oh. The user cancelled the Twitter login.")
//            } else if (user.isNew) {
//
//                Toast.makeText(
//                    this@LoginActivity,
//                    "User signed up and logged in through Twitter.",
//                    Toast.LENGTH_LONG
//                ).show()
//                Log.d("MyApp", "User signed up and logged in through Twitter!")
//                user.username = ParseTwitterUtils.getTwitter().screenName
//                user.saveInBackground(object : SaveCallback {
//                    override fun done(e: ParseException?) {
//                        if (null == e) {
//                            alertDisplayer("First tome login!", "Welcome!")
//                        } else {
//                            ParseUser.logOut()
//                            Toast.makeText(
//                                this@LoginActivity,
//                                "It was not possible to save your username.",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        }
//                    }
//                })
//            } else {
//
//                Toast.makeText(
//                    this@LoginActivity,
//                    "User logged in through Twitter.",
//                    Toast.LENGTH_LONG
//                ).show()
//                Log.d("MyApp", "User logged in through Twitter!")
//                alertDisplayer("Oh, you!", "Welcome back!")
//            }
//        }
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

//    private fun alertDisplayer(title: String, message: String) {
//        val builder: AlertDialog.Builder? = AlertDialog.Builder(this@LoginActivity)
//            .setTitle(title)
//            .setMessage(message)
//            .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
//                dialog.cancel()
//                // don't forget to change the line below with the names of your Activities
//                val intent = Intent(this@LoginActivity, MainActivity::class.java)
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(intent)
//            })
//        val ok: AlertDialog? = builder?.create()
//        ok?.show()
//    }


}