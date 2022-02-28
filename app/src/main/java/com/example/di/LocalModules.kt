package com.example.di

import com.example.data.datasource.remote.datasource.RemoteDataSource
import com.example.data.datasource.remote.datasource.impl.RemoteDataSourceImpl
import com.example.data.repository.ActivityRepository
import com.example.data.repository.impl.ActivityRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalModules {

    @Binds
    abstract fun bindMainRepo(
        mainRepoImpl: ActivityRepositoryImpl
    ): ActivityRepository

    @Binds
    abstract fun bindMainRemoteDataSource(
        mainRemoteDataSource: RemoteDataSourceImpl
    ): RemoteDataSource
}