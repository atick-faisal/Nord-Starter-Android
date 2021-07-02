package ai.andromeda.nordstarter.storage.datastore

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    suspend fun saveThemePreference(theme: String)
    fun loadThemePreference(): Flow<String>
}