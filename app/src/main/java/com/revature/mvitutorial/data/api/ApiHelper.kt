package com.revature.mvitutorial.data.api

import com.revature.mvitutorial.data.model.User
import retrofit2.http.GET

interface ApiHelper {
    suspend fun getUsers():List<User>
}

interface ApiService{
    @GET("users")
    suspend fun getUsers():List<User>
}
class ApiHelperImpl(private val apiService: ApiService):ApiHelper{
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}