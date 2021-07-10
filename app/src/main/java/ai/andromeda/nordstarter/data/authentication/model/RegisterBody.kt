package ai.andromeda.nordstarter.data.authentication.model

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
