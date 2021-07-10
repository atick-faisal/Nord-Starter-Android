package ai.andromeda.nordstarter.data.home.model

import com.google.gson.annotations.SerializedName

data class DummyItem(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("logo") val logo: String? = null
)
