package com.example.recepiappkotlin.activities

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recepiappkotlin.R
import com.example.recepiappkotlin.manager.AppPreferenceManager
import com.example.recepiappkotlin.viewmodels.DatabaseViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MyRecipes : AppCompatActivity() {

    lateinit var recycleView: RecyclerView
    lateinit var add: FloatingActionButton
    lateinit var dialog: Dialog
    lateinit var saveBtn: Button
    lateinit var title: EditText
    lateinit var desc: EditText
    lateinit var choose: Button
    lateinit var filePath: Uri
    lateinit var image: ImageView
    lateinit var random: String
    var rep = DatabaseViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()


        var manager : AppPreferenceManager = AppPreferenceManager(this)

        if(manager.getDarkModeState() == false){
            setTheme(R.style.AppTheme) }
        else {
            setTheme(R.style.AppThemeDark)
        }

        setContentView(R.layout.activity_my__recepies)

        recycleView = findViewById(R.id.recycleView)
        add = findViewById(R.id.addNew)
        filePath = "notDefined".toUri()

        recycleView.setLayoutManager(LinearLayoutManager(this))
        rep.getData(recycleView, this)

        add.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                showDialog()
            }
        })
    }

    private fun showDialog(): Unit {
        dialog = Dialog(this)
        dialog.setContentView(R.layout.input_dialog)
        dialog.show()

        title = dialog.findViewById(R.id.titleEditText)
        desc = dialog.findViewById(R.id.desdEditText)
        saveBtn = dialog.findViewById(R.id.saveBtn)
        image = dialog.findViewById(R.id.imageView)
        choose = dialog.findViewById(R.id.choose)

        choose.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, 1000)
            }

        })

        saveBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (title.getText().length == 0 || desc.getText().length == 0) {
                    Toast.makeText(getContext(), "Cant leave empty field!", Toast.LENGTH_SHORT)
                        .show()
                } else if (title.getText().length > 91 || desc.getText().length > 91) {
                    Toast.makeText(getContext(), "Text must be shorter", Toast.LENGTH_SHORT).show()
                } else {
                    random = UUID.randomUUID().toString()
                    rep.input(
                        title.getText().toString(), desc.getText().toString(), random, filePath
                    )

                    dialog.dismiss()
                    filePath = "notDefined".toUri()
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1000 && data != null && data.data != null) {
            filePath = data!!.data!!
            image?.setImageURI(data?.data)
        }
    }

    private fun getContext(): Context {
        return this
    }
}

