DeepL-Translate Design Project 
===

# DeepL Translate

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Tweet or Comment in instagram or make a google search in any language that you want to. 


### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Reference
- **Mobile:** This app would be primarily developed for mobile but would perhaps be just as viable on a computer. Functionality of translate wouldn’t be limited to mobile devices, however mobile version would be more practical for day to day use.
- **Story:** This would help expats from different countries engage with social media in the country that they are in. 
- **Market:** Marketed to cater to tourists.  Use case would be connecting non-English speakers to the internet in the US. Useful for international brands trying to cater to the local marketplace.
- **Habit:** This app can be used whenever a foreign language is encoutered for a brief 
- **Scope:** First we'll have people use it as hashtags on their post and hope provides enough publicity and if we get funding it would be used to develop app more and advertising.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Use the DeepL API to perform the translation process
* Get the translated output 
* Connect twitter account, or embed google search links in the app
* Query the translated output as an input to the social media API. 
* User is able to translate English input to any language they want

**Optional Nice To-have Stories**

* User can select input language
*
**MILESTONE 1: ENDS HERE**

### 2. Screen Archetypes

* Login - User signs up or logs into their account
   * [Optional] Users are given the option to skip login if they just want to use the translate app
* Translate/Main screen - User translates their given message and posts to social media
   * Ability to select language translated to
   * [Optional] Ability to select language translated from
   * Allow API calls to post translated message
   * Redirects to Login screen if attempt to post without login in.
   * [Optional] Ability to sign out of accounts
* [Optional] Previous translations - Display previous translations stored in own API
    * [Optional] Ability to delete entries

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Login Screen
* Translate Screen
* [Optional] Previous Translations

**Flow Navigation** (Screen to Screen)

* Login -> Translate if user logins or skips
* Translate -> Login if user attempts to post without logining in
* [Optional] History -> Translate if user clicks back icon
* [Optional] Translate -> History if user clicks history icon

## Wireframes

<img src="https://i.imgur.com/DbDXM16.png" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
#### Model: Language
|Property          |Type    |Description 
|------------------|--------|-----------
|objectId          |String  |Unique id for language
|Name              |String  |Name of the language
|createdAt         |DateTime|Time language was created
|updatedAt         |DateTime|Time language was updated

#### Model: User
|Property          |Type    |Description 
|------------------|--------|-----------
|objectId          |String  |Unique id for user
|igUsername        |String  |User's instagram username
|igPassword        |String  |User's instagram password
|twitterUsername   |String  |User's twitter username
|twitterPassword   |String  |User's twitter password
|createdAt         |DateTime|Time user was created
|updatedAt         |DateTime|Time user was updated

#### Model: OriginalPost
|Property          |Type            |Description 
|------------------|----------------|-----------
|objectId          |String          |Unique id for original post
|user              |Pointer to user |Owner of the post 
|description       |String          |Text description of the post
|createdAt         |DateTime        |Time original post was created
|updatedAt         |DateTime        |Time original post was updated

#### Model: TranslatedPost
|Property          |Type                   |Description 
|------------------|-----------------------|-----------
|objectId          |String                 |Unique id for user
|languageId        |Pointer to language    |Language of the translated post
|user              |Pointer to user        |Owner of the post
|originalPost      |Pointer to originalPost|Original pretranslated post
|description       |String                 |Translated text description
|createdAt         |DateTime               |Time translated post was created
|updatedAt         |DateTime               |Time translated post was updated
### Networking
#### Add list of network requests by screen 
- Compose screen
    - (Read/GET) Get list of languages user can translate to
        ```kotlin
        val query: ParseQuery<Language> = ParseQuery.getQuery(Language::class.java)

        query.findInBackground(object: FindCallback<Language> {
            override fun done(languages: MutableList<Language>?, e: ParseException?) {
                if (e != null)
                    Log.e(TAG, "Error fetching posts")
                else 
                    //TODO: Populate list of languages to display
            }
        })
        ```
    - (Create/POST) Create a new post in english
      ```kotlin
       // create the Post obj
        val post = OriginalPost()
        // TODO: set data fields of OriginalPost obj according to the input entered by user
        post.saveInBackground{ exception ->
            if (exception != null)
                Log.e(MainActivity.TAG, "Error saving post")
            else 
                Log.i(MainActivity.TAG, "Successfully saved post")
      ```
    - (Create/POST) Create a new translated post
      ```kotlin
       // create the Post obj
        val post = TranslatedPost()
        // TODO: set data fields of TranslatedPost obj according to the input entered by user
        post.saveInBackground{ exception ->
            if (exception != null)
                Log.e(MainActivity.TAG, "Error saving post")
            else 
                Log.i(MainActivity.TAG, "Successfully saved post")
      ```
    - (Read/GET) Get the translated post
        ```kotlin
            val query: ParseQuery<TranslatedPost> = ParseQuery.getQuery(TranslatedPost::class.java)

            query.findInBackground(object: FindCallback<TranslatedPost> {
                override fun done(translatedPost: TranslatedPost?, e: ParseException?) {
                    if (e != null)
                        Log.e(TAG, "Error fetching posts")
                    else 
                        //TODO: Populate translate text box with translated post
                }
            })
        ```
        
- History screen
    - (Read/GET) Get all posts previously made through the app
        ```kotlin
        val query: ParseQuery<OriginalPost> = ParseQuery.getQuery(Language::class.java)

        query.findInBackground(object: FindCallback<OriginalPost> {
            override fun done(originalPosts: MutableList<OriginalPost>?, e: ParseException?) {
                if (e != null)
                    Log.e(TAG, "Error fetching posts")
                else 
                    //TODO: Populate list of original posts to display
            }
        })
        ```
- [OPTIONAL: List of endpoints of existing API]
#### Twitter API
#### Base URL - https://api.twitter.com/1.1

|HTTP Verb|Endpoint                     |Description
|---------|-----------------------------|--------------------------------
|`POST`   |statuses/update.json         |Publishes tweet

#### Instagram API
#### Base URL - https://graph.facebook.com/v13.0

|HTTP Verb|Endpoint                               |Description
|---------|---------------------------------------|--------------------------------
|`POST`   |{ig-user-id}/mediastatuses/update.json |Publishes instagram post

### References
- https://developer.twitter.com/en/docs/twitter-api/v1/tweets/post-and-engage/api-reference/post-statuses-update
- https://developers.facebook.com/docs/instagram-api/reference/ig-user/media#creating
- https://developers.facebook.com/docs/graph-api/guides/versioning
