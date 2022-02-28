package com.example.data.datasource.remote.datasource.impl

import com.example.data.datasource.remote.api.ApiService
import com.example.data.datasource.remote.datasource.RemoteDataSource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: ApiService) : RemoteDataSource {

    override suspend fun getActivity() = api.getRandomActivity()
}
