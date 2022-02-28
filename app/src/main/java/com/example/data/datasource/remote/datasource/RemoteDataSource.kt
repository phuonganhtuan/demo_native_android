package com.example.data.datasource.remote.datasource

import com.example.data.models.ActivityEntity

interface RemoteDataSource {

    suspend fun getActivity(): ActivityEntity
}
