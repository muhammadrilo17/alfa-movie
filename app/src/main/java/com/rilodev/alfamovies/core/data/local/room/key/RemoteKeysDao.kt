package com.rilodev.alfamovies.core.data.local.room.key

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.rilodev.alfamovies.core.data.local.helpers.TableConstants.REMOTE_KEYS_TABLE

@Dao
interface RemoteKeysDao {
    @Query("SELECT * FROM $REMOTE_KEYS_TABLE WHERE id = :id")
    suspend fun getRemoteKeysId(id: Int): RemoteKeys?

    @Insert(onConflict = REPLACE)
    suspend fun insertRemoteKeys(remoteKey: List<RemoteKeys>)

    @Query("DELETE FROM $REMOTE_KEYS_TABLE")
    suspend fun deleteRemoteKeys()

    @Query("SELECT * FROM $REMOTE_KEYS_TABLE ORDER BY next_key DESC LIMIT 1")
    suspend fun getRemoteKeysForLastItem(): RemoteKeys?

}