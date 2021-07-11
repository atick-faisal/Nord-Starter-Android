package ai.andromeda.nordstarter.data.authentication.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResponse(
    @SerializedName("success") val success: Boolean
)
