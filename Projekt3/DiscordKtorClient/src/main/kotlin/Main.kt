package com.example.discord

import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.PrivilegedIntent
import io.ktor.client.statement.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


val products = listOf(
    Product(1, "Smartfon X", "Elektronika", 1999.99),
    Product(2, "Laptop Y", "Elektronika", 3999.99),
    Product(3, "Kurtka zimowa", "Moda", 299.99),
    Product(4, "Stół do jadalni", "Dom i ogród", 499.99),
    Product(5, "Piłka nożna", "Sport", 89.99),
    Product(6, "Nowa książka", "Książki", 59.99)
)

@OptIn(PrivilegedIntent::class)
suspend fun startBot(token: String) {
    val kord = Kord(token)

    kord.on<MessageCreateEvent> {
        val content = message.content.trim()

        if (content.equals("!categories", ignoreCase = true)) {
            val categories = listOf("Elektronika", "Moda", "Dom i ogród", "Sport", "Książki")
            val response = "Lista kategorii: ${categories.joinToString(", ")}"
            message.channel.createMessage(response)
        }


        if (content.startsWith("!products", ignoreCase = true)) {
            val parts = content.split(" ", limit = 2)
            if (parts.size < 2) {
                message.channel.createMessage("Podaj nazwę kategorii, np. !products Elektronika")
            } else {
                val requestedCategory = parts[1].trim()
                val filteredProducts = products.filter { it.category.equals(requestedCategory, ignoreCase = true) }
                if (filteredProducts.isEmpty()) {
                    message.channel.createMessage("Brak produktów w kategorii \"$requestedCategory\"")
                } else {
                    val response = filteredProducts.joinToString(separator = "\n") { product ->
                        "ID: ${product.id}, Nazwa: ${product.name}, Cena: ${product.price} zł"
                    }
                    message.channel.createMessage("Produkty w kategorii \"$requestedCategory\":\n$response")
                }
            }
        }
    }

    println("Bot jest uruchamiany...")
    kord.login {
        intents += dev.kord.gateway.Intent.MessageContent
    }
}


fun main() = runBlocking {
    val webhookUrl = "https://discordapp.com/api/webhooks/1359647749792075947/cCp-Wi06Q2iMgFGRfmC8XDnahHehYDQhKAkGQMiyfND8Xzo38O3MyjXyd2DLhYuD7fdY"
    val messageContent = "Cześć! To wiadomość wysłana z Ktor."

    try {
        val response = sendDiscordMessage(webhookUrl, messageContent)
        println("Status odpowiedzi przy wysylaniu: ${response.status}")
        val responseBody: String = response.bodyAsText()
        println("Tresc odpowiedzi: $responseBody")
    } catch (e: Exception) {
        println("Wystąpil blad podczas wysylania wiadomosci: ${e.message}")
    }

    val botToken = "YourToken" // Wstaw tutaj swój token bota Discord


    launch {
        startBot(botToken)
    }.join()
}
