package ai.andromeda.nordstarter.data.home

import ai.andromeda.nordstarter.data.home.model.DummyItem
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeRestApi {
    @GET("restaurant/random_restaurant/")
    suspend fun getItems(@Query("size") size: Int): List<DummyItem>
}