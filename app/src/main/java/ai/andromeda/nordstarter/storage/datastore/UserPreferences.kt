package ai.andromeda.nordstarter.storage.datastore

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    suspend fun saveThemePreference(theme: String)
    fun loadThemePreference(): Flow<String>
    suspend fun saveLoginStatus(loginStatus: Boolean)
    fun getLoginStatus(): Flow<Boolean>
}