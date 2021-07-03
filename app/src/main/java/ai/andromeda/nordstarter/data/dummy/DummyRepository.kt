package ai.andromeda.nordstarter.data.dummy

import ai.andromeda.nordstarter.storage.room.entity.Item
import ai.andromeda.nordstarter.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DummyRepository {
    fun getItems(): Flow<Resource<List<Item>>>
}