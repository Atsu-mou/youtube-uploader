package com.example.auth

import kotlin.test.Test
import kotlin.test.assertEquals

class AuthDecisionTest {
    @Test
    fun `returns REAUTH when snapshot is null`() {
        val action = AuthDecision.decide(snapshot = null, nowMillis = 0L)
        assertEquals(AuthAction.REAUTH, action)
    }

    @Test
    fun `returns USE when credential expires in 5 minutes`() {
        val now = 1_000_000L
        val snapshot =
            CredentialSnapshot(
                accessToken = "at",
                refreshToken = "rt",
                expiresAtMillis = now + 300_000L,
            )
        assertEquals(AuthAction.USE, AuthDecision.decide(snapshot, now))
    }

    @Test
    fun `returns REAUTH when refresh token is null`() {
        val snapshot =
            CredentialSnapshot(
                accessToken = "at",
                refreshToken = null,
                expiresAtMillis = 9_999_999_999L,
            )
        assertEquals(AuthAction.REAUTH, AuthDecision.decide(snapshot, nowMillis = 0L))
    }

    @Test
    fun `returns REFRESH when access token is null`() {
        val snapshot =
            CredentialSnapshot(
                accessToken = null,
                refreshToken = "rt",
                expiresAtMillis = 9_999_999_999L,
            )
        assertEquals(AuthAction.REFRESH, AuthDecision.decide(snapshot, nowMillis = 0L))
    }
}
