package com.example.recepiappkotlin.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel

import androidx.recyclerview.widget.RecyclerView
import com.example.recepiappkotlin.repositories.DatabaseRepository


class DatabaseViewModel: ViewModel(){

    private var repository = DatabaseRepository()

    fun getData (recyclerView: RecyclerView, context : Context){
        repository.getData(recyclerView, context)
    }

    fun input(title: String, desc: String, image: String, pathFile: Uri) {
        repository.input(title, desc, image, pathFile);
    }
}
