package com.example.artstation.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtDao { // suspend fun are those that can be paused and resumed when it needed
     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertArt(art: Art)
     @Delete
     suspend fun deleteArt(art:Art)
     @Query(value = "SELECT * from arts")
     fun observeArts():LiveData<List<Art>>
}