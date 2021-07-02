package ai.andromeda.nordstarter.data.default

import ai.andromeda.nordstarter.data.default.model.DefaultItem
import retrofit2.http.GET
import retrofit2.http.Query

interface DefaultRestApi {
    @GET("restaurant/random_restaurant/")
    fun getItems(
        @Query("size") size: Int
    ): List<DefaultItem>
}