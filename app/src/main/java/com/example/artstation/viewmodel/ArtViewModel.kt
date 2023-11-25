package com.example.artstation.viewmodel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artstation.model.ImageResponse
import com.example.artstation.repo.ArtRepository
import com.example.artstation.repo.ArtRepositoryinterface
import com.example.artstation.roomdb.Art
import com.example.artstation.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArtViewModel @Inject constructor(
    private  val repository: ArtRepositoryinterface
): ViewModel() {


    val  artList = repository.getArt()

    // image api fragment

    private  val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList : LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage  = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String>
        get() = selectedImage


    // Art details fragment

    private var insertArtMsg = MutableLiveData<Resource<Art>>()
    val insertArtMessage : LiveData<Resource<Art>>
        get() = insertArtMsg


    fun resetInsertArtMsg(){
        insertArtMsg = MutableLiveData<Resource<Art>>()
    }

    fun setSelectedImage(url : String){
        selectedImage.postValue(url)
    }

    fun deleteArt(art: Art) = viewModelScope.launch{
        repository.deleteArt(art)
    }

    fun insertArt(art: Art) = viewModelScope.launch {
        repository.insertArt(art)
    }

    fun  makeArt(name : String , artistName : String, year: String){
        if(name.isEmpty() || artistName.isEmpty()|| year.isEmpty()){
            insertArtMsg.postValue(Resource.error("Enter name , artist , year" , null))
            return
        }
        val yearInt = try {
            year.toInt()
        }catch (e: Exception){
            insertArtMsg.postValue(Resource.error("year should be number", null))
            return
        }
        val art = Art(name, artistName, yearInt, selectedImage.value ?:"")
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }

    fun searchForImage(searchString : String){

        if(searchString.isEmpty()){
            return
        }

        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            images.value = response
        }


    }


}