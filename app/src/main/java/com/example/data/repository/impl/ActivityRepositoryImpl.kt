package com.example.data.repository.impl

import com.example.data.datasource.remote.datasource.RemoteDataSource
import com.example.data.repository.ActivityRepository
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : ActivityRepository {

    override suspend fun getActivity() = remoteDataSource.getActivity()
}
