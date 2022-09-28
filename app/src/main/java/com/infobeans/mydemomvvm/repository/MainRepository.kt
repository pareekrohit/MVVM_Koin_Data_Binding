package com.infobeans.mydemomvvm.repository


import com.infobeans.mydemomvvm.model.Post
import com.infobeans.mydemomvvm.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository
/*@Inject*/
constructor(
   private val apiService: ApiService
) {

    fun getPost(): Flow<List<Post>> = flow {
        emit(apiService.getPost())
    }.flowOn(Dispatchers.IO)

}