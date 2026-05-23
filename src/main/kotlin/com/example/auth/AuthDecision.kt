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
        if (snapshot == null) return AuthAction.REAUTH
        if (snapshot.refreshToken == null) return AuthAction.REAUTH
        if (snapshot.accessToken == null) return AuthAction.REFRESH
        return AuthAction.USE
    }
}
