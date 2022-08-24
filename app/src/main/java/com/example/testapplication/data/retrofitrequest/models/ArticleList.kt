package com.example.testapplication.data.retrofitrequest.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleList(
    @SerializedName("articles")
    val articles:List<Article>,
): Parcelable