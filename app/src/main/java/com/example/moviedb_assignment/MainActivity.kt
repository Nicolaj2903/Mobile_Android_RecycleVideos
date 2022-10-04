package com.example.moviedb_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val exampleList = generateDummyList(500)

        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)

        recycler_view.adapter = ExampleAdapter(exampleList)
        recycler_view.layoutManager = LinearLayoutManager(this) // Linear layout (up & down)
        recycler_view.setHasFixedSize(true)
    }

                               // Size: amount of items in the list
    private fun generateDummyList(size: Int): List<ExampleItem> {

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
