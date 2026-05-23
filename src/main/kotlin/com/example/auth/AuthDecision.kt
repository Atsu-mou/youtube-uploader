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
    ): AuthAction =
        when {
            snapshot?.refreshToken == null -> AuthAction.REAUTH
            snapshot.accessToken == null -> AuthAction.REFRESH
            snapshot.expiresAtMillis != null && snapshot.expiresAtMillis <= nowMillis -> AuthAction.REFRESH
            else -> AuthAction.USE
        }
}
