package com.example.auth

import kotlin.test.Test
import kotlin.test.assertEquals

class AuthDecisionTest {
    @Test
    fun `snapshot が null なら REAUTH を返す`() {
        val action = AuthDecision.decide(snapshot = null, nowMillis = 0L)
        assertEquals(AuthAction.REAUTH, action)
    }
}
