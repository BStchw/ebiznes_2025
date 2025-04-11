package com.example.discord

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

suspend fun sendDiscordMessage(webhookUrl: String, content: String): HttpResponse {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }
    val message = DiscordMessage(content)
    val response = client.post(webhookUrl) {
        contentType(ContentType.Application.Json)
        setBody(message)
    }
    client.close()
    return response
}