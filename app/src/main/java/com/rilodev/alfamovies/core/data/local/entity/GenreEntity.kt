package com.rilodev.alfamovies.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rilodev.alfamovies.core.data.local.helpers.TableConstants

@Entity(tableName = TableConstants.GENRE_TABLE)
data class GenreEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(index = true, name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
)
