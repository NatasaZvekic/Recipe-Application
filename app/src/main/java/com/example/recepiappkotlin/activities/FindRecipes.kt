package com.example.recepiappkotlin.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recepiappkotlin.R
import com.example.recepiappkotlin.adapter.APIAdapter
import com.example.recepiappkotlin.adapter.onClickListener
import com.example.recepiappkotlin.models.APIModel
import com.example.recepiappkotlin.manager.AppPreferenceManager
import com.example.recepiappkotlin.viewmodels.APIViewModel
import kotlinx.android.synthetic.main.activity_find_recipes.*

class FindRecipes : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    private val recipe = java.util.ArrayList<APIModel>()
    lateinit var adapter: APIAdapter
    private var v = APIViewModel()
    lateinit var viewModel: APIViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var manager : AppPreferenceManager = AppPreferenceManager(this)
        if(manager.getDarkModeState() == false){
            setTheme(R.style.AppTheme)
        }
        else {
            setTheme(R.style.AppThemeDark)
        }

        setContentView(R.layout.activity_find_recipes)

        supportActionBar?.hide()
        var search = findViewById<SearchView>(R.id.searchView)


        viewModel = ViewModelProvider(this).get(APIViewModel::class.java)



        viewModel.getRecipes().observe(this, Observer<List<APIModel>> { recipeDatabase ->

            adapter = APIAdapter(baseContext, recipeDatabase as MutableList<APIModel>)
            adapter.notifyDataSetChanged()
            recyclerMovieList2.adapter = adapter
            recyclerMovieList2.setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@FindRecipes)
            recyclerMovieList2.layoutManager = layoutManager

            adapter.setOnItemClickListener(object  : onClickListener{


                override fun onItemClick(recipe: APIModel) {
                    val intent = Intent(getContext(), RecepiDetails::class.java).apply {
                        putExtra("recipe", recipe)
                    }
                    startActivity(intent)
                }

            })

        })



        search.setOnQueryTextListener(  object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean { return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                search.clearFocus()
                if (query != null) {
                    viewModel.searchRecipes(query)
                }
                search.clearFocus()

                return false
            }

        })

    }

    fun getContext() : Context { return this}



//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.search_item,menu)
//        val searchItem = menu.findItem(R.id.app_bar_search)
//        if(searchItem != null){
//            val searchView = searchItem.actionView as SearchView
//            searchView.clearFocus()
//
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    if (query != null) {
//                        viewModel.searchRecipes(query)
//                    }
//                    searchView.clearFocus()
//
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//
//                    return true
//                }
//
//            })
//        }
//
//        return super.onCreateOptionsMenu(menu)
//    }


}
