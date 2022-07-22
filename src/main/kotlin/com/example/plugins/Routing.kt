package com.example.plugins

import com.example.cache.InMemoryCache
import com.example.features.login.loginRouting
import com.example.features.register.registerRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable

fun Application.routingConfigure(){
    routing {
        loginRouting()
        registerRouting()

        get("/users") {
            call.respond(InMemoryCache.users)
        }
    }
}
