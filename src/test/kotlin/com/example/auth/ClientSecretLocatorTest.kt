package com.example.auth

import java.io.ByteArrayInputStream
import java.io.InputStream
import kotlin.test.Test
import kotlin.test.assertNotNull

class ClientSecretLocatorTest {
    @Test
    fun `opens default path when env not set`() {
        val fakeStream: InputStream = ByteArrayInputStream("{}".toByteArray())
        val locator =
            ClientSecretLocator(
                envLookup = { null },
                resourceLookup = { path -> if (path == "/client_secret.json") fakeStream else null },
            )
        assertNotNull(locator.open())
    }
}
