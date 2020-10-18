package com.example.recepiappkotlin.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.recepiappkotlin.models.APIModel
import com.example.recepiappkotlin.request.Hits
import com.example.recepiappkotlin.request.RecipeInfo
import com.example.recepiappkotlin.models.APIModelz
import com.example.recepiappkotlin.request.Interface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIRepository {

    private var recipe = ArrayList<APIModel>()
    private var liveDataList = MediatorLiveData<List<APIModel>>()

    fun getRecipes() : LiveData<List<APIModel>>{
        return  liveDataList
    }

    fun searchRecipes (query : String) : Unit{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.edamam.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val recipeAPI = retrofit.create(Interface::class.java)
        recipeAPI.getRecipe(query, "39a04542", "816218941c786167c991c322c6359221")
            .enqueue(object : Callback<RecipeInfo> {
                override fun onFailure(call: Call<RecipeInfo>, t: Throwable) {
                }

                override fun onResponse(call: Call<RecipeInfo>, response: Response<RecipeInfo>) {
                    if(response.body()!=null) {
                        var hitsArrayList: ArrayList<Hits> = response.body()!!.getHits1()
                        recipe.clear()

                        for (hits in hitsArrayList) {
                            recipe.add(hits.getRecepi())


                        }
                        liveDataList.postValue(recipe)
                    }

                }
            })

    }
}