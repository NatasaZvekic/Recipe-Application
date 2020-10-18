package com.example.recepiappkotlin.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.recepiappkotlin.models.APIModel
import com.example.recepiappkotlin.models.APIModelz
import com.example.recepiappkotlin.repositories.APIRepository

class APIViewModel : ViewModel() {

    private var repository = APIRepository()

    fun searchRecipes(query : String) : Unit{
        repository.searchRecipes(query)
    }

    fun getRecipes() : LiveData<List<APIModel>>{
        return repository.getRecipes()
    }
}