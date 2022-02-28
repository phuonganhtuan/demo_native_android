package com.example.data.models

import kotlin.random.Random

data class ActivityEntity(
    override var id: Int = Random.nextInt(),
    val accessibility: Double?,
    val activity: String?,
    val key: String?,
    val link: String?,
    val participants: Int?,
    val price: Double?,
    val type: String?,
) : BaseEntity()
