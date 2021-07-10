package ai.andromeda.nordstarter.data.authentication.model

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @SerializedName("success") val success: Boolean
)
