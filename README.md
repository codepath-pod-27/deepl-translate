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
|user              |Pointer to user        |Owner of the post
|originalPost      |Pointer to originalPost|Original pretranslated post
|description       |String                 |Translated text description
|createdAt         |DateTime               |Time translated post was created
|updatedAt         |DateTime               |Time translated post was updated
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
