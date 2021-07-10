package ai.andromeda.nordstarter.di.network

import ai.andromeda.nordstarter.data.authentication.AuthenticationRestApi
import ai.andromeda.nordstarter.data.home.HomeRestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        RetrofitModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object RestServiceModule {

    @Singleton
    @Provides
    fun provideHomeRestApi(retrofit: Retrofit): HomeRestApi {
        return retrofit.create(HomeRestApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthenticationRestApi(retrofit: Retrofit): AuthenticationRestApi {
        return retrofit.create(AuthenticationRestApi::class.java)
    }

}