package ai.andromeda.nordstarter.di.repository

import ai.andromeda.nordstarter.data.authentication.AuthenticationRepository
import ai.andromeda.nordstarter.data.authentication.AuthenticationRepositoryImpl
import ai.andromeda.nordstarter.data.home.HomeRepository
import ai.andromeda.nordstarter.data.home.HomeRepositoryImpl
import ai.andromeda.nordstarter.data.settings.SettingsRepository
import ai.andromeda.nordstarter.data.settings.SettingsRepositoryImpl
import ai.andromeda.nordstarter.storage.preferences.UserPreferences
import ai.andromeda.nordstarter.storage.preferences.UserPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserPreferences(
        userPreferencesImpl: UserPreferencesImpl
    ): UserPreferences

    @Binds
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository

    @Binds
    abstract fun bindAuthenticationRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    abstract fun bindSettingsRepository(
        settingsRepositoryImpl: SettingsRepositoryImpl
    ): SettingsRepository

}