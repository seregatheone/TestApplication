package com.example.testapplication.data

import com.example.testapplication.data.retrofitrequest.RequestService
import javax.inject.Inject

class RetrofitRequestRepository @Inject constructor(private val apiRequests: RequestService) {
    suspend fun getArticles() = apiRequests.getRequest()
}
