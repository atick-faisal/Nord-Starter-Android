package ai.andromeda.nordstarter.database.datastore

import ai.andromeda.nordstarter.database.datastore.data.UserPreferences
import kotlinx.coroutines.flow.Flow

interface PreferencesStore {
    suspend fun saveUserPreferences(userPreferences: UserPreferences)
    fun loadUserPreferences(): Flow<UserPreferences>
}