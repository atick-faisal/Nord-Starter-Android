package ai.andromeda.nordstarter.data.settings

import ai.andromeda.nordstarter.storage.preferences.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val userPreferences: UserPreferences,
): SettingsRepository {

    override fun getThemeSettings(): Flow<String> {
        return userPreferences.getThemePreference()
    }

    override suspend fun saveThemePreferences(theme: String) {
        userPreferences.saveThemePreference(theme)
    }

}