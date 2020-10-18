package com.example.recepiappkotlin.request

import com.example.recepiappkotlin.request.RecipeInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Interface {
    @GET("search")
    fun getRecipe(@Query("q") q: String, @Query("app_id") appId: String, @Query("app_key") appKey: String): Call<RecipeInfo>

}

