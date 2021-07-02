package ai.andromeda.nordstarter.data.default.model

import ai.andromeda.nordstarter.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DefaultRepository {
    fun getItems(): Flow<Resource<List<DefaultItem>>>
}