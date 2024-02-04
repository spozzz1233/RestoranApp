package com.example.restoranapp.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restoranapp.domain.item.ItemInteractor
import com.example.restoranapp.domain.model.MenuItem
import com.example.restoranapp.domain.model.item
import kotlinx.coroutines.launch

class OrderViewModel(
    private val interactor: ItemInteractor
) : ViewModel() {

    private val _resultsListLiveData = MutableLiveData<ArrayList<MenuItem>>()
    val resultsListLiveData: LiveData<ArrayList<MenuItem>> =
        _resultsListLiveData


    fun getItem() {
        viewModelScope.launch {
            interactor
                .getItem()
                .collect {
                    processResult(it.data)
                }
        }
        _resultsListLiveData.value = item

    }

    fun processResult(itemes: List<MenuItem>?) {
        val item = ArrayList<MenuItem>()
        if (itemes != null) {
            item.addAll(itemes)
            _resultsListLiveData.value = item
        }
    }

}