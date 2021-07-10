package ai.andromeda.nordstarter.data.authentication

import ai.andromeda.nordstarter.data.authentication.model.AuthenticationResponse
import ai.andromeda.nordstarter.data.authentication.model.LoginBody
import ai.andromeda.nordstarter.data.authentication.model.RegisterBody
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun login(body: LoginBody): AuthenticationResponse
    suspend fun register(body: RegisterBody): AuthenticationResponse
    suspend fun saveLoginStatus(loginStatus: Boolean)
}