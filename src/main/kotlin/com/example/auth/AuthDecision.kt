package com.example.auth

enum class AuthAction { USE, REFRESH, REAUTH }

data class CredentialSnapshot(
    val accessToken: String?,
    val refreshToken: String?,
    val expiresAtMillis: Long?,
)

object AuthDecision {
    fun decide(
        snapshot: CredentialSnapshot?,
        nowMillis: Long,
    ): AuthAction {
        return AuthAction.REAUTH
    }
}
