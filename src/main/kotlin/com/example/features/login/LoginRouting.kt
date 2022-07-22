package com.example.features.login

import com.example.cache.InMemoryCache
import com.example.cache.TokenCache
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Routing.loginRouting() {
    get("/login") {
        val loginReceive = call.receive<LoginReceiveRemote>()
        InMemoryCache.users.find { it.login == loginReceive.login } ?: return@get call.respondText(
            "Not find user",
            status = HttpStatusCode.NotFound
        )

        val token = UUID.randomUUID().toString()
        InMemoryCache.tokenList.add(
            TokenCache(
                loginReceive.login,
                token
            )
        )
        call.respond(LoginResponseRemote(token))
    }

}