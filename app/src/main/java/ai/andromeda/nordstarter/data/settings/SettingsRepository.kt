package ai.andromeda.nordstarter.data.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getThemeSettings(): Flow<String>
    suspend fun saveThemePreferences(theme: String)
}