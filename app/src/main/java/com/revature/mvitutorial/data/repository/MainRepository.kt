package com.revature.mvitutorial.data.repository

import com.revature.mvitutorial.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}