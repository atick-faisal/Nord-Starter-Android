package ai.andromeda.nordstarter.data.home

import ai.andromeda.nordstarter.storage.room.entity.Item
import ai.andromeda.nordstarter.utils.Resource
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getItems(numItems: Int): Flow<Resource<List<Item>>>
    fun getLoginStatus(): Flow<Boolean>
}