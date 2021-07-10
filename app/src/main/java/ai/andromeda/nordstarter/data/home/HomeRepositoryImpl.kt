package ai.andromeda.nordstarter.data.home

import ai.andromeda.nordstarter.storage.datastore.UserPreferences
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
    private val userPreferences: UserPreferences,
    private val homeRestApi: HomeRestApi
) : HomeRepository {

    override fun getItems(numItems: Int): Flow<Resource<List<Item>>> {
        return networkBoundResource(
            query = {
                dao.getAllItems()
            },
            fetch = {
                homeRestApi.getItems(numItems)
            },
            saveFetchedResult = { dummyItemList ->
                database.withTransaction {
                    dao.clear()
                    dao.insertAll(
                        dummyItemList.map { dummyItem ->
                            Item(
                                name = dummyItem.name,
                                description = dummyItem.description,
                                logo = dummyItem.logo
                            )
                        }
                    )
                }
            }
        )
    }

    override fun getLoginStatus(): Flow<Boolean> {
        return userPreferences.getLoginStatus()
    }
}