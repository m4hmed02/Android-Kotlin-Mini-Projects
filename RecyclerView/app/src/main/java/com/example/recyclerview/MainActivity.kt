package com.example.recyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.Adaptor.ExampleAdaptor
import com.example.recyclerview.Model.ExampleItem

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptor: ExampleAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        val exampleList = generateList(100)
        adaptor = ExampleAdaptor(this, exampleList)
        recyclerView.adapter = adaptor

        // it is responsible for position the list , and Linear Layout Manager create a vertical list
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }
}

fun generateList(size: Int) : MutableList<ExampleItem> {
    val list = mutableListOf<ExampleItem>()

    for (i in 0 until size){
        list.add(ExampleItem("Title $i", "Description $i"))
    }

    return list
}
