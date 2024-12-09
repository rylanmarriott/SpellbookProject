package com.example.dndspells

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dndspells.api.ApiClient
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val spellName: TextView = findViewById(R.id.spellName)
        val spellLevel: TextView = findViewById(R.id.spellLevel)
        val spellDescription: TextView = findViewById(R.id.spellDescription)

        val spellIndex = intent.getStringExtra("spell_index")

        if (spellIndex != null) {
            lifecycleScope.launch {
                try {
                    val spellDetails = ApiClient.retrofit.getSpellDetails(spellIndex)
                    spellName.text = spellDetails.name
                    spellLevel.text = "Level: ${spellDetails.level}"

                    if (spellDetails.desc.isNullOrEmpty()) {
                        spellDescription.text = "No description available."
                    } else {
                        spellDescription.text = spellDetails.desc.joinToString("\n")
                    }
                } catch (e: Exception) {
                    spellDescription.text = "Failed to load details."
                }
            }
        }
    }

    // Handle the Up button click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
