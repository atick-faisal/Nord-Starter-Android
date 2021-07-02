package ai.andromeda.nordstarter.storage.room

import ai.andromeda.nordstarter.storage.room.entity.Item
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM items WHERE id = :id")
    suspend fun getItem(id: Long): Item?

    @Query("SELECT * FROM items WHERE item_name = :name LIMIT 1")
    suspend fun getItem(name: String): Item?

    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<Item>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<Item>)

    @Query("DELETE FROM items")
    suspend fun clear()
}