package ai.andromeda.nordstarter.di.local

import ai.andromeda.nordstarter.database.room.AppDatabase
import ai.andromeda.nordstarter.utils.ROOM_DATABASE_NAME
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext appContext: Context
    ) = Room.databaseBuilder(appContext, AppDatabase::class.java, ROOM_DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

}