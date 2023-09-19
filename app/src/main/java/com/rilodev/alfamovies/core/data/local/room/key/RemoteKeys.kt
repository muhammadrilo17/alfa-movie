package com.rilodev.alfamovies.core.data.local.room.key

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rilodev.alfamovies.core.data.local.helpers.TableConstants

@Entity(tableName = TableConstants.REMOTE_KEYS_TABLE)
data class RemoteKeys(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "movie_flag")
    var movieFlag: String,
    @ColumnInfo(name = "prev_key")
    var prevKey: Int?,
    @ColumnInfo(name = "next_key")
    var nextKey: Int?
)