package com.example.recepiappkotlin.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class APIModel() : Parcelable{

    @SerializedName("label")
    var title : String? = null

    @SerializedName("image")
    var image_url : String? = null

    var calories : Double? = null

    @SerializedName("totalTime")
    var time : Int? = null

    @SerializedName("ingredientLines") //to znaci da tamo pise ovako
    var  ingredients :  ArrayList<String>?= null

     var dietLabels: ArrayList<String>? = null

    @SerializedName("yield")
    var numberOfServings: Int? = null

     var healthLabels: ArrayList<String>? = null

     var cautions: ArrayList<String>? = null

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()
        image_url = parcel.readString()
        calories = parcel.readValue(Double::class.java.classLoader) as? Double
        time = parcel.readValue(Int::class.java.classLoader) as? Int
        numberOfServings = parcel.readValue(Int::class.java.classLoader) as? Int
        ingredients = parcel.readValue(Array<String>::class.java.classLoader) as? ArrayList<String>
        healthLabels = parcel.readValue(Array<String>::class.java.classLoader) as? ArrayList<String>
        cautions = parcel.readValue(Array<String>::class.java.classLoader) as? ArrayList<String>
        dietLabels = parcel.readValue(Array<String>::class.java.classLoader) as? ArrayList<String>


    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(image_url)
        parcel.writeValue(calories)
        parcel.writeValue(time)
        parcel.writeValue(numberOfServings)
        parcel.writeValue(ingredients)
        parcel.writeValue(dietLabels)
        parcel.writeValue(cautions)
        parcel.writeValue(healthLabels)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<APIModel> {
        override fun createFromParcel(parcel: Parcel): APIModel {
            return APIModel(parcel)
        }

        override fun newArray(size: Int): Array<APIModel?> {
            return arrayOfNulls(size)
        }
    }


}