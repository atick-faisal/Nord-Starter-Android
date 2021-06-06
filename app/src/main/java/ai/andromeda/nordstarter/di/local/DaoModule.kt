package ai.andromeda.nordstarter.di.local

import ai.andromeda.nordstarter.database.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        DatabaseModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun provideItemDao(appDatabase: AppDatabase) = appDatabase.getItemDao()

}