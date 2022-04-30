package com.example.translatorapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast




import android.content.Intent
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import com.parse.SignUpCallback
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.Twitter.initialize
import com.twitter.sdk.android.core.identity.TwitterLoginButton


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
        ParseUser.logOut()

//        queryHistory()

//        val firstObject = ParseObject("FirstClass")
//        firstObject.put("message","Hey ! Second message from android. Parse is now connected")
//        firstObject.saveInBackground {
//            if (it != null) {
//                it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
//            } else {
//                Log.d("MainActivity", "Object saved.")
//            }
//        }

       initialize(this);

        loginButton = findViewById(R.id.login_button)
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

    open fun queryHistory(){
        val query: ParseQuery<TranslationHistory> = ParseQuery.getQuery(TranslationHistory::class.java)//.setLimit(1) <- works too
//        query.include(TranslationHistory.KEY_USER) // star
//        query.addDescendingOrder("createdAt")
        query.findInBackground(object: FindCallback<TranslationHistory> {
            override fun done(posts: MutableList<TranslationHistory>?, e: ParseException?) {
                if (e != null) {
                    Log.e(MainActivity.TAG, "Error fetching posts")
                } else {
                    if (posts != null){
//                        adapter.clear()
                        for (post in posts){
                            Log.i(TAG, "Input: " + post.getInput() + ", username: " + post.getUser()?.username)
                        }
//                        allPosts.addAll(posts)
//                        adapter.notifyDataSetChanged()
//                        swipeContainer.setRefreshing(false)
//                        Toast.makeText(requireContext(), "Successfully refreshed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }
    companion object {
        val TAG = "LoginActivity"
    }

    fun loginMethod(twitterSession: TwitterSession) {
        val user = ParseUser();
        val userName = twitterSession.userName
        user.setUsername(userName)
        user.setPassword("passtest")
        user.signUpInBackground(SignUpCallback() {
            if (it == null) {
                Log.i("Successful Sign Up!", "Welcome " + user.getUsername() + "!");
            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show();
            }
        });
        val accessToken = twitterSession.authToken.token
        val accessSecret = twitterSession.authToken.secret
        val intent = Intent(this, MainActivity::class.java)
        ParseUser.logInInBackground(userName, "passtest", ({user, e->
            if(user != null){
                Log.i(TAG, "Successfully logged in user")
            } else {
                Log.e(TAG, "FAIL")
                e.printStackTrace()
            }
        }))
        intent.putExtra("username", userName)
        intent.putExtra("access_token", accessToken)
        intent.putExtra("accessSecret", accessSecret)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data)
    }




}