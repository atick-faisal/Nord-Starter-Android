package ai.andromeda.nordstarter.data.authentication

import ai.andromeda.nordstarter.data.authentication.model.AuthenticationResponse
import ai.andromeda.nordstarter.data.authentication.model.LoginBody
import ai.andromeda.nordstarter.data.authentication.model.RegisterBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationRestApi {
    @POST("auth/login/")
    suspend fun login(@Body body: LoginBody): AuthenticationResponse

    @POST("auth/register")
    suspend fun register(@Body body: RegisterBody): AuthenticationResponse
}