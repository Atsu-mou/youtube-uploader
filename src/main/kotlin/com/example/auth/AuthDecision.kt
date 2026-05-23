package com.example.auth

enum class AuthAction { USE, REFRESH, REAUTH }

data class CredentialSnapshot(
    val accessToken: String?,
    val refreshToken: String?,
    val expiresAtMillis: Long?,
)

object AuthDecision {
    private const val EXPIRY_SKEW_MS = 60_000L

    fun decide(
        snapshot: CredentialSnapshot?,
        nowMillis: Long,
    ): AuthAction =
        when {
            snapshot?.refreshToken == null -> AuthAction.REAUTH
            snapshot.accessToken == null -> AuthAction.REFRESH
            snapshot.expiresAtMillis != null && snapshot.expiresAtMillis <= nowMillis + EXPIRY_SKEW_MS -> AuthAction.REFRESH
            else -> AuthAction.USE
        }
}
