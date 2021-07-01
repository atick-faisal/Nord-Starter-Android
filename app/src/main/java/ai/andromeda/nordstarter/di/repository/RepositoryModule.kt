package ai.andromeda.nordstarter.di.repository

import ai.andromeda.nordstarter.database.datastore.UserPreferences
import ai.andromeda.nordstarter.database.datastore.UserPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserPreferences(userPreferencesImpl: UserPreferencesImpl): UserPreferences

}