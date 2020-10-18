package com.example.recepiappkotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.recepiappkotlin.R
import com.example.recepiappkotlin.models.APIModel
import com.example.recepiappkotlin.manager.AppPreferenceManager
import com.squareup.picasso.Picasso

class RecepiDetails : AppCompatActivity() {

    private var time : TextView? = null
    private var calories : TextView? = null
    private var title : TextView? = null
    private var number : TextView? = null
    private var ingredients: TextView? = null
    private var source: TextView? = null
    private var cautions: TextView? = null
    private var health: TextView? = null
    private var diet: TextView? = null
    private var image: ImageView? = null
    private var mIngredients: TextView? = null
    private var mDiet: TextView? = null
    private var mHealth: TextView? = null
    private var mCautions: TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        var manager : AppPreferenceManager = AppPreferenceManager(this)
        if(manager.getDarkModeState() == false){
            setTheme(R.style.AppTheme)
        }
        else {
            setTheme(R.style.AppThemeDark)
        }
        setContentView(R.layout.activity_recepi_details)

        val myRecipe : APIModel = intent.getParcelableExtra("recipe")

        time = findViewById(R.id.time)
        calories = findViewById(R.id.calories)
        title = findViewById(R.id.title)
        number = findViewById(R.id.number)
        image = findViewById(R.id.image)
        mIngredients = findViewById(R.id.ingredientsTextView)
        mDiet = findViewById(R.id.dietTextView)
        mHealth = findViewById(R.id.healthTextView)
        mCautions = findViewById(R.id.cautionsTextView)


        setProperties(myRecipe)


    }

    fun setProperties(recipe : APIModel) : Unit{
        title?.setText(recipe.title)

        Picasso.get() //link ucita u sliku
            .load(recipe.image_url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(image)

        if(!recipe.time!!.equals(0)) {
            var num : Int = recipe.time!!
            var numS : String = num.toString()
            time?.setText("Time: \n" + numS)
        }
        var num : Int = recipe.numberOfServings!!
        var numS : String = num.toString()
        number?.setText(numS)

        val calories1 = recipe.calories as Double
        val number3digits: Int = calories1.toInt()
        var c : String = number3digits.toString()
        calories?.setText("Calories: \n " + c)

        val stringBuilder = StringBuilder()
        for (s in recipe.ingredients!!) {
            stringBuilder.append("• $s\n")
        }
        mIngredients?.setText(stringBuilder.toString())

        if (recipe.healthLabels!!.size > 0) {
            val stringBuilder2 = StringBuilder()
            stringBuilder2.append("HEALTH:")
            for (s in recipe.healthLabels!!) {

                stringBuilder2.append("\n" + s)
            }
            mHealth?.setText(stringBuilder2.toString())
        }

        if (recipe.dietLabels!!.size > 0) {
            val stringBuilder3 = StringBuilder()
            stringBuilder3.append("DIET:")
            for (s in recipe.dietLabels!!) {
                stringBuilder3.append("\n" + s)
            }
            mDiet?.setText(stringBuilder3.toString())
        }

        if (recipe.cautions!!.size > 0) {
            val stringBuilder4 = StringBuilder()
            stringBuilder4.append("CAUTIONS:")
            for (s in recipe.cautions!!) {
                stringBuilder4.append("\n" + s)
            }
            mCautions?.setText(stringBuilder4.toString())
        }


    }
}
