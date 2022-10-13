package com.example.moviedb_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListener {

    private val exampleList = generateDummyList(500)
    private val adapter = ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this) // Linear layout (up & down)
        recyclerView.setHasFixedSize(true)


        // Retrofit
        var retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var jsonAPI : MovieInterface = retrofit.create(MovieInterface::class.java)

        // Data call
        var call : Call<List<Movie>> = jsonAPI.getMovies();
    }

    fun insertItem(view: View) {
        val index: Int = Random.nextInt(8)

        val newItem = ExampleItem(
            R.drawable.ic_android,
            text1 = "New item at position $index",
            text2 = "Line 2"
        )

        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }

    fun removeItem(view: View) {
        val index = Random.nextInt(8)

        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {

        // Toast: Small notification when clicking an item
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()

        val clickedItem: ExampleItem = exampleList[position]
        clickedItem.text1 = "Clicked"
        adapter.notifyItemChanged(position)
    }

    // Size: amount of items in the list
    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {

        val list = ArrayList<ExampleItem>()

        // Fill the list with data
        for (i in 0 until size)
        {
            // Alternative between the drawables
            val drawable = when (i % 3)
            {
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_speedy_wheelchair
                else -> R.drawable.ic_blind_guy
            }

            val item = ExampleItem(drawable, "item $i", "Line 2")
            list += item
        }
        return list
    }
}
