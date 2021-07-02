package ai.andromeda.nordstarter.data.default.model

import ai.andromeda.nordstarter.data.default.DefaultRestApi
import ai.andromeda.nordstarter.storage.room.ItemDao
import ai.andromeda.nordstarter.storage.room.entity.Item
import ai.andromeda.nordstarter.utils.Resource
import ai.andromeda.nordstarter.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultRepositoryImpl @Inject constructor(
    private val database: ItemDao,
    private val api: DefaultRestApi
) : DefaultRepository {
    override fun getItems(): Flow<Resource<List<DefaultItem>>> {
        return networkBoundResource(
            query = {
                database.getAllItems().map {
                    it.map { item ->
                        DefaultItem(
                            name = item.name
                        )
                    }
                }
            },
            fetch = {
                api.getItems(20)
            },
            saveFetchedResult = {
                it.map { defaultItem ->
                    Item(
                        name = defaultItem.name
                    )
                }
            }
        )
    }
}