package com.example.bonchapp.router

import com.example.bonchapp.domain.entities.Auth
import com.example.bonchapp.domain.entities.Token

object User {
    lateinit var auth: Auth
    lateinit var token: Token

    fun create(username: String, password: String): User{
        auth = Auth(username, password)
        return this
    }

    fun addToken(token: Token){
        this.token = token
    }

}
