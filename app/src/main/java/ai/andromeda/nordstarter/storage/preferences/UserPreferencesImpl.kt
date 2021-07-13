package ai.andromeda.nordstarter.storage.preferences

import ai.andromeda.nordstarter.storage.preferences.state.LoginStatus
import ai.andromeda.nordstarter.storage.preferences.state.ThemeState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserPreferencesImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferences {

    companion object {
        val THEME_KEY = stringPreferencesKey("nord_theme_preference")
        val LOGIN_STATUS_KEY = stringPreferencesKey("nord_login_status")
    }

    override suspend fun saveThemePreference(theme: String) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme
        }
    }

    override fun getThemePreference(): Flow<String> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[THEME_KEY] ?: ThemeState.SYSTEM.name
        }
    }

    override suspend fun saveLoginStatus(loginStatus: Boolean) {
        dataStore.edit { preferences ->
            if (loginStatus) preferences[LOGIN_STATUS_KEY] = LoginStatus.LOGGED_IN.name
            else preferences[LOGIN_STATUS_KEY] = LoginStatus.LOGGED_OUT.name
        }
    }

    override fun getLoginStatus(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            when (preferences[LOGIN_STATUS_KEY]) {
                LoginStatus.LOGGED_IN.name -> true
                LoginStatus.LOGGED_OUT.name -> false
                else -> false
            }
        }
    }
}