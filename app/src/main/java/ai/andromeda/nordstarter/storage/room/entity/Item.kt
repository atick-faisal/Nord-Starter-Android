package ai.andromeda.nordstarter.storage.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "items"
)
@Serializable
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "item_name") val name: String,
    @ColumnInfo(name = "item_description") val description: String? = null,
    @ColumnInfo(name = "item_logo") val logo: String? = null
)
