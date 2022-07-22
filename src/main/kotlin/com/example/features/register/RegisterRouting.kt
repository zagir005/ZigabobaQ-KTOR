package com.example.features.register

import com.example.cache.InMemoryCache
import com.example.cache.TokenCache
import com.example.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Routing.registerRouting(){
    post("/register") {
        val registerReceive = call.receive<RegisterReceiveRemote>()
        if (!registerReceive.email.isValidEmail()) {
            return@post call.respond(HttpStatusCode.BadRequest, "Email is not valid",)
        }

        if (InMemoryCache.users.find { it.login == registerReceive.login } != null){
            return@post call.respond(HttpStatusCode.Conflict, "User already exists")
        }

        val token = UUID.randomUUID().toString()
        InMemoryCache.tokenList.add(TokenCache(registerReceive.login,token))
        InMemoryCache.users.add(registerReceive)

        call.respond(RegisterResponseRemote(token))

    }
}