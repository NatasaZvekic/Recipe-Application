package com.example.recepiappkotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.example.recepiappkotlin.R
import com.example.recepiappkotlin.manager.AppPreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var findRecipes : ImageView
    lateinit var myRecipes : ImageView
    lateinit var settings: FloatingActionButton

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

        setContentView(com.example.recepiappkotlin.R.layout.activity_main)


        findRecipes = findViewById(R.id.find_recipe12)
        myRecipes = findViewById(R.id.my_recipe)
        settings = findViewById(R.id.settings)


        findRecipes.setOnClickListener {
            var intent = Intent(this, FindRecipes::class.java)
            startActivity(intent)
        }


        myRecipes.setOnClickListener {
            var intent = Intent(this, MyRecipes::class.java)
            startActivity(intent)
        }


        settings.setOnClickListener(object  : View.OnClickListener{
            override fun onClick(v: View?) {
                var intent = Intent(this@MainActivity, Settings::class.java)
                startActivity(intent)            }

        })

    }

}




