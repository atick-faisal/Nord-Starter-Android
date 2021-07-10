package ai.andromeda.nordstarter.di.repository

import ai.andromeda.nordstarter.data.home.HomeRepository
import ai.andromeda.nordstarter.data.home.HomeRepositoryImpl
import ai.andromeda.nordstarter.storage.datastore.UserPreferences
import ai.andromeda.nordstarter.storage.datastore.UserPreferencesImpl
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

}