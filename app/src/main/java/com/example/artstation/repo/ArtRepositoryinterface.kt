package com.example.artstation.repo

import androidx.lifecycle.LiveData
import com.example.artstation.model.ImageResponse
import com.example.artstation.roomdb.Art
import com.example.artstation.util.Resource

interface ArtRepositoryinterface {
    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art:Art)

    fun getArt(): LiveData<List<Art>>

    suspend fun searchImage(imageString: String): Resource<ImageResponse>

}