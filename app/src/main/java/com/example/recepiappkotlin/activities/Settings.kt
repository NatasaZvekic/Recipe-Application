package com.example.recepiappkotlin.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.cardview.widget.CardView
import com.example.recepiappkotlin.R
import com.example.recepiappkotlin.manager.AppPreferenceManager

class Settings : AppCompatActivity() {

    lateinit var card : CardView
    lateinit var appPreferenceManager : AppPreferenceManager

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
        setContentView(R.layout.activity_settings)

        appPreferenceManager = AppPreferenceManager(this)


        card = findViewById(R.id.cardView)
        card.setOnClickListener(object  : View.OnClickListener{
            override fun onClick(v: View?) {
                val builder = AlertDialog.Builder(this@Settings)
                builder.setTitle("Dark Mode")
                    .setMessage("Enabling/Disabling dark mode requires app UI to restart! Do you want to continue?")
                    .setPositiveButton("Yes") { dialogInterface, i ->
                        if (appPreferenceManager.getDarkModeState()) {
                            darkMode(false)
                        } else {
                            darkMode(true)
                        }
                    }.setNegativeButton("No", null)
                    .create().show()
                }
            })
        }


    fun darkMode(enable : Boolean) {
        appPreferenceManager.setDarkModeState(enable)
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
