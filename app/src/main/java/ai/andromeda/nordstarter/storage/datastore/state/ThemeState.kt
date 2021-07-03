package ai.andromeda.nordstarter.storage.datastore.state

import androidx.datastore.preferences.core.stringPreferencesKey

val THEME_KEY = stringPreferencesKey("nord_theme_preference")

enum class ThemeState { LIGHT, DARK, SYSTEM }

