package ai.andromeda.nordstarter.data.home

import ai.andromeda.nordstarter.storage.room.AppDatabase
import ai.andromeda.nordstarter.storage.room.ItemDao
import ai.andromeda.nordstarter.storage.room.entity.Item
import ai.andromeda.nordstarter.utils.Resource
import ai.andromeda.nordstarter.utils.networkBoundResource
import androidx.room.withTransaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val dao: ItemDao,
    private val api: HomeRestApi
) : HomeRepository {

    override fun getItems(): Flow<Resource<List<Item>>> {
        return networkBoundResource(
            query = {
                dao.getAllItems()
            },
            fetch = {
                api.getItems(20)
            },
            saveFetchedResult = {
                database.withTransaction {
                    dao.clear()
                    dao.insertAll(
                        it.map { defaultItem ->
                            Item(
                                name = defaultItem.name
                            )
                        }
                    )
                }
            }
        )
    }
}