package com.example.testapplication.data.retrofitrequest.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Article(
    @SerializedName("author")
    val author:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("urlToImage")
    val urlToImage:String,
    @SerializedName("url")
    val url:String,
    @SerializedName("publishedAt")
    val publishedAt: Date,
    @SerializedName("content")
    val content:String): Parcelable
