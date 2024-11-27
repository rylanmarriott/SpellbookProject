package com.example.dndspells.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dndspells.model.Spell

class SpellAdapter(
    private val spells: List<Spell>,
    private val onClick: (Spell) -> Unit
) : RecyclerView.Adapter<SpellAdapter.SpellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return SpellViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpellViewHolder, position: Int) {
        val spell = spells[position]
        holder.bind(spell, onClick)
    }

    override fun getItemCount(): Int = spells.size

    class SpellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(spell: Spell, onClick: (Spell) -> Unit) {
            textView.text = spell.name
            itemView.setOnClickListener { onClick(spell) }
        }
    }
}
