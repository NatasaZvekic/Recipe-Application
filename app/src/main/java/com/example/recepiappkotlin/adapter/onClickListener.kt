package com.example.recepiappkotlin.adapter

import com.example.recepiappkotlin.models.APIModel
import com.example.recepiappkotlin.models.APIModelz

interface onClickListener {
     fun onItemClick(recipe : APIModel) : Unit

}