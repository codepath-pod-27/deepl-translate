package com.example.translatorapi

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName("history")
class TranslationHistory : ParseObject() {
    fun getInput(): String? {
        return getString(KEY_INPUT)
    }
    fun setInput(description: String){
        put(KEY_INPUT, description)
    }
    fun getOutput(): String? {
        return getString(KEY_OUTPUT)
    }
    fun setOutput(description: String){
        put(KEY_OUTPUT, description)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }
    fun setUser(user: ParseUser){
        put(KEY_USER, user)
    }

    companion object{
        const val KEY_INPUT = "input"
        const val KEY_OUTPUT = "output"
        const val KEY_USER = "user"

    }
}