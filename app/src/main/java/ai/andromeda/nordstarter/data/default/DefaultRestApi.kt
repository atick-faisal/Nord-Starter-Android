package ai.andromeda.nordstarter.data.default

import ai.andromeda.nordstarter.data.default.model.DefaultItem
import retrofit2.Call
import retrofit2.http.GET

interface DefaultRestApi {
    @GET("posts")
    fun getItems(): Call<List<DefaultItem>>
}