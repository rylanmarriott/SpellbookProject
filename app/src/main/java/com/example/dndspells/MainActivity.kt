package com.example.dndspells

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dndspells.adapter.SpellAdapter
import com.example.dndspells.viewmodel.SpellViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: SpellViewModel by viewModels()
    private lateinit var adapter: SpellAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe spells LiveData
        viewModel.spells.observe(this) { spells ->
            adapter = SpellAdapter(spells) { spell ->
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("spell_index", spell.index)
                startActivity(intent)
            }
            recyclerView.adapter = adapter
        }

        // Fetch spells from the API
        viewModel.fetchSpells()
    }
}
