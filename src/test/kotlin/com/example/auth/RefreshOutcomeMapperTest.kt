package com.example.auth

import java.io.IOException
import kotlin.test.Test
import kotlin.test.assertEquals

class RefreshOutcomeMapperTest {
    @Test
    fun `fromResult returns SUCCESS when refresh succeeded`() {
        assertEquals(RefreshOutcome.SUCCESS, RefreshOutcomeMapper.fromResult(ok = true))
    }

    @Test
    fun `fromResult returns REAUTH_REQUIRED when refresh fails`() {
        assertEquals(RefreshOutcome.REAUTH_REQUIRED, RefreshOutcomeMapper.fromResult(ok = false))
    }

    @Test
    fun `fromException returns FAILED for IOException`() {
        assertEquals(RefreshOutcome.FAILED, RefreshOutcomeMapper.fromException(IOException("network down")))
    }
}