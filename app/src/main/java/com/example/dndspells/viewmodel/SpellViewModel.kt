package com.example.dndspells.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndspells.api.ApiClient
import com.example.dndspells.model.Spell
import kotlinx.coroutines.launch

class SpellViewModel : ViewModel() {
    private val _spells = MutableLiveData<List<Spell>>()
    val spells: LiveData<List<Spell>> get() = _spells

    fun fetchSpells() {
        viewModelScope.launch {
            try {
                val response = ApiClient.retrofit.getSpells()
                _spells.postValue(response.results)
            } catch (e: Exception) {
                // Handle errors (e.g., log or notify)
                e.printStackTrace()
            }
        }
    }
}
