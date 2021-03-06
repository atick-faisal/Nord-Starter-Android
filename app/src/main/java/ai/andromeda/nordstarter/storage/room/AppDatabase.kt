package ai.andromeda.nordstarter.storage.room

import ai.andromeda.nordstarter.storage.room.entity.Item
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        Item::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getItemDao(): ItemDao
}