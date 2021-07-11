package ai.andromeda.nordstarter.data.authentication.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
