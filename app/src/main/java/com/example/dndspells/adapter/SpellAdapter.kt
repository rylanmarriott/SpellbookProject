package com.example.dndspells.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dndspells.R
import com.example.dndspells.model.Spell

class SpellAdapter(
    private val spells: List<Spell>,
    private val onClick: (Spell) -> Unit
) : RecyclerView.Adapter<SpellAdapter.SpellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_spell, parent, false)
        return SpellViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val spell = spells[position]
        holder.bind(spell, onClick)
    }

    override fun getItemCount(): Int = spells.size

    class SpellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val spellName: TextView = itemView.findViewById(R.id.spellName)

        fun bind(spell: Spell, onClick: (Spell) -> Unit) {
            spellName.text = spell.name
            itemView.setOnClickListener { onClick(spell) }
        }
    }
}
