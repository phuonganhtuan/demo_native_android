package com.example.data.repository

import com.example.data.models.ActivityEntity

interface ActivityRepository {

    suspend fun getActivity(): ActivityEntity
}
