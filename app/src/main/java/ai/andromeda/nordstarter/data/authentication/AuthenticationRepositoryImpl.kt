package ai.andromeda.nordstarter.data.authentication

import ai.andromeda.nordstarter.data.authentication.model.AuthenticationResponse
import ai.andromeda.nordstarter.data.authentication.model.LoginBody
import ai.andromeda.nordstarter.data.authentication.model.RegisterBody
import ai.andromeda.nordstarter.storage.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val userPreferences: UserPreferences,
    private val authenticationRepository: AuthenticationRepository
) : AuthenticationRepository {

    override suspend fun login(body: LoginBody): AuthenticationResponse {
        return authenticationRepository.login(body)
    }

    override suspend fun register(body: RegisterBody): AuthenticationResponse {
        return authenticationRepository.register(body)
    }

    override suspend fun saveLoginStatus(loginStatus: Boolean) {
        userPreferences.saveLoginStatus(loginStatus)
    }

}