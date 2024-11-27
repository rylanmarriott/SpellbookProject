package com.example.dndspells

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.dndspells.api.ApiClient
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

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
                    spellDescription.text = spellDetails.description.joinToString("\n")
                } catch (e: Exception) {
                    spellDescription.text = "Failed to load details."
                }
            }
        }
    }
}
