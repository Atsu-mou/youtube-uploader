package com.example.auth

import com.example.auth.RefreshOutcome.FAILED
import com.example.auth.RefreshOutcome.REAUTH_REQUIRED
import com.example.auth.RefreshOutcome.SUCCESS

enum class RefreshOutcome { SUCCESS, REAUTH_REQUIRED, FAILED }

object RefreshOutcomeMapper {
    fun fromResult(ok: Boolean): RefreshOutcome = if (ok) SUCCESS else REAUTH_REQUIRED

    fun fromException(e: Throwable): RefreshOutcome = FAILED
}
