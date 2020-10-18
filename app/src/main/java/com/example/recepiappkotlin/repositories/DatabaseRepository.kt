package com.example.recepiappkotlin.repositories

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.recepiappkotlin.models.DatabaseModel
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.FirebaseStorage
import android.content.Context
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recepiappkotlin.adapter.DatabaseAdapter
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import java.util.ArrayList


class DatabaseRepository {
    var ref : DatabaseReference?=null
    lateinit var recyclerView2: RecyclerView
    lateinit var reference: StorageReference
    lateinit var storage: FirebaseStorage
    lateinit var storageReference: StorageReference
    lateinit var adapter: DatabaseAdapter
    lateinit var layoutManager: LinearLayoutManager



    fun getData(recyclerView: RecyclerView, baseContext  :Context):Unit{

        recyclerView2 = recyclerView
        var ref = FirebaseDatabase.getInstance().getReference("RecipeDatabase1")
        var hitsArrayList = ArrayList<DatabaseModel>()

        ref.addValueEventListener(object  : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                hitsArrayList.clear()
                for(h in p0.children){
                    val hero = h.getValue(DatabaseModel::class.java)
                    hero?.key = h.key
                    if (hero != null) {
                        hitsArrayList.add(hero)
                    }
                }
                adapter = DatabaseAdapter(baseContext, hitsArrayList as MutableList<DatabaseModel>)
                adapter.notifyDataSetChanged()
                recyclerView2.adapter = adapter
                recyclerView2.setHasFixedSize(true)
                layoutManager = LinearLayoutManager(baseContext)
                recyclerView2.layoutManager = layoutManager
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }



    fun input(title: String, desc: String, random: String, pathFile: Uri) {
        storage = FirebaseStorage.getInstance()
        storageReference = storage.getReference()

        if (pathFile != "notDefined".toUri()) {
            reference = storageReference.child("images/$random")
            reference.putFile(pathFile)
                .addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> {
                    reference.getDownloadUrl().addOnSuccessListener(
                        OnSuccessListener<Uri> { uri -> insertData(title, desc, uri.toString()) })
                })
        } else {
            insertData(title, desc, "https://thumbs.dreamstime.com/z/no-image-available-icon-flat-vector-no-image-available-icon-flat-vector-illustration-132484366.jpg"
            )
        }
    }

    fun insertData(title: String, desc: String, imageUrl: String) {
        val b = DatabaseModel()
        b.title = title
        b.desc = desc
        b.image = imageUrl

        ref = FirebaseDatabase.getInstance().getReference("RecipeDatabase1")

        ref?.push()?.setValue(b)
    }

    fun deleteRecipe(key: String) {
        ref = FirebaseDatabase.getInstance().getReference("RecipeDatabase1").child(key)
        ref?.removeValue()

    }

}