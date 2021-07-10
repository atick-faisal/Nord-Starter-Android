package ai.andromeda.nordstarter.data.authentication

import ai.andromeda.nordstarter.data.authentication.model.AuthenticationResponse
import ai.andromeda.nordstarter.data.authentication.model.LoginBody
import ai.andromeda.nordstarter.data.authentication.model.RegisterBody
import ai.andromeda.nordstarter.storage.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val userPreferences: UserPreferences,
    private val authenticationRestApi: AuthenticationRestApi
) : AuthenticationRepository {

    override suspend fun login(body: LoginBody): AuthenticationResponse {
        return authenticationRestApi.login(body)
    }

    override suspend fun register(body: RegisterBody): AuthenticationResponse {
        return authenticationRestApi.register(body)
    }

    override suspend fun saveLoginStatus(loginStatus: Boolean) {
        userPreferences.saveLoginStatus(loginStatus)
    }

}