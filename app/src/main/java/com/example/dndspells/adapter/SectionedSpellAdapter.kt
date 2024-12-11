package com.example.dndspells.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dndspells.R
import com.example.dndspells.model.Spell

class SectionedSpellAdapter(
    private val spellSections: Map<Int, List<Spell>>,
    private val onClick: (Spell) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val flattenedList: List<Item> = spellSections.flatMap { (level, spells) ->
        listOf(Item.Header(level)) + spells.map { Item.SpellItem(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false)
                HeaderViewHolder(view)
            }
            ViewType.ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.spell_item, parent, false) // Use spell_item.xml
                SpellViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    class SpellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val spellName: TextView = itemView.findViewById(R.id.spellName)
        private val spellLevel: TextView = itemView.findViewById(R.id.spellLevel)

        fun bind(spell: Spell, onClick: (Spell) -> Unit) {
            spellName.text = spell.name
            spellLevel.text = "Level: ${spell.level}"
            itemView.setOnClickListener { onClick(spell) }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = flattenedList[position]) {
            is Item.Header -> (holder as HeaderViewHolder).bind(item.level)
            is Item.SpellItem -> (holder as SpellViewHolder).bind(item.spell, onClick)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (flattenedList[position]) {
            is Item.Header -> ViewType.HEADER
            is Item.SpellItem -> ViewType.ITEM
        }
    }

    override fun getItemCount(): Int = flattenedList.size

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(android.R.id.text1)
        fun bind(level: Int) {
            textView.text = "Level $level"
        }
    }

    sealed class Item {
        data class Header(val level: Int) : Item()
        data class SpellItem(val spell: Spell) : Item()
    }

    object ViewType {
        const val HEADER = 0
        const val ITEM = 1
    }
}
