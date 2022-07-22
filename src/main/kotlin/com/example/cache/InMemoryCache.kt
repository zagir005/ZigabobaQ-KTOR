package com.example.cache

import com.example.features.register.RegisterReceiveRemote

data class TokenCache(
    val login: String,
    val token: String
)

object InMemoryCache{
    val users = mutableListOf<RegisterReceiveRemote>()
    val tokenList = mutableListOf<TokenCache>()
}