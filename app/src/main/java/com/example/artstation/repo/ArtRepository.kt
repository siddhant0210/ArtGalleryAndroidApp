package com.example.artstation.repo

import androidx.lifecycle.LiveData
import com.example.artstation.api.RetrofitAPI
import com.example.artstation.model.ImageResponse
import com.example.artstation.roomdb.Art
import com.example.artstation.roomdb.ArtDao
import com.example.artstation.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(private val artDao: ArtDao, private val retrofitAPI: RetrofitAPI) : ArtRepositoryinterface{
    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitAPI.imageSearch(imageString)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?: Resource.error("Error" , null)
            }
            else{
                Resource.error("Error" , null)
            }
        }catch (e: Exception){
            Resource.error("No Data@", null)
        }
    }


}