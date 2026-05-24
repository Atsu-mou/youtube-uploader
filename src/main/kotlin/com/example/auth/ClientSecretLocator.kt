package com.example.auth

import java.io.InputStream

class ClientSecretLocator(
    private val envLookup: (String) -> String? = System::getenv,
    private val resourceLookup: (String) -> InputStream? = { ClientSecretLocator::class.java.getResourceAsStream(it) },
) {
    fun open(): InputStream {
        val path = envLookup("SECRET_KEY") ?: DEFAULT_PATH
        return resourceLookup(path) ?: error("client secret not found at $path")
    }

    companion object {
        const val DEFAULT_PATH = "/client_secret.json"
    }
}
