package ai.andromeda.nordstarter.storage.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    suspend fun saveThemePreference(theme: String)
    fun getThemePreference(): Flow<String>
    suspend fun saveLoginStatus(loginStatus: Boolean)
    fun getLoginStatus(): Flow<Boolean>
}