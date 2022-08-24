package com.example.testapplication.data.retrofitrequest



import com.example.testapplication.data.retrofitrequest.models.ArticleList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RequestService {
    @GET("v2/everything?q=kotlin")
    suspend fun getRequest(): ArticleList
}

fun requestService(apiKey: String): RequestService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val authorizedRequest = chain.request().newBuilder()
                .addHeader(HEADER_API_KEY,apiKey)
                .build()
            chain.proceed(authorizedRequest)
        }
        .build()

    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://newsapi.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(RequestService::class.java)
}
private const val HEADER_API_KEY = "X-Api-Key"