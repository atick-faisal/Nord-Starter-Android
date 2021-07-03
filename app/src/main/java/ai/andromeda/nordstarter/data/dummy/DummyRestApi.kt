package ai.andromeda.nordstarter.data.dummy

import ai.andromeda.nordstarter.data.dummy.model.DummyItem
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyRestApi {
    @GET("restaurant/random_restaurant/")
    suspend fun getItems(@Query("size") size: Int): List<DummyItem>
}