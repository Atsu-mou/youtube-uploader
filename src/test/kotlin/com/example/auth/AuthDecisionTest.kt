package com.example.auth

import kotlin.test.Test
import kotlin.test.assertEquals

class AuthDecisionTest {
    @Test
    fun `snapshot が null なら REAUTH を返す`() {
        val action = AuthDecision.decide(snapshot = null, nowMillis = 0L)
        assertEquals(AuthAction.REAUTH, action)
    }

    @Test
    fun `5分後に切れるなら USE を返す`() {
        val now = 1_000_000L
        val snapshot =
            CredentialSnapshot(
                accessToken = "at",
                refreshToken = "rt",
                expiresAtMillis = now + 300_000L,
            )
        assertEquals(AuthAction.USE, AuthDecision.decide(snapshot, now))
    }
}
