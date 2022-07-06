package com.example.edvora.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.edvora.RideRepository
import com.example.edvora.data.RidesX
import com.example.edvora.data.user
import com.example.edvora.extra.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class viewModel(val repo:RideRepository): ViewModel(){
    val rides: MutableLiveData<Resource<RidesX>> = MutableLiveData()
    val user1:MutableLiveData<Resource<user>> = MutableLiveData()
init {
    getuser()
    getallRides()

}
fun getallRides()=viewModelScope.launch {
    val respnse=repo.getAllRides()
    rides.postValue(handleResponse(respnse))
}
    fun getuser()=viewModelScope.launch {
        val respo=repo.getuser()
        user1.postValue(handleuserResponse(respo))
    }
    private fun handleResponse(response: Response<RidesX>) : Resource<RidesX> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
    private fun handleuserResponse(response: Response<user>) : Resource<user> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}