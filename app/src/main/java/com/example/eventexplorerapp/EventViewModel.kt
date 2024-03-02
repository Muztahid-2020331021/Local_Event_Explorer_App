package com.example.eventexplorerapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventexplorerapp.data.EventList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class EventViewModel : ViewModel(){
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()


    private val _events = MutableStateFlow(EventList.scheduledEvents)
    val events = searchText
        .onEach { _isSearching.update { true } }
        .combine(_events){text,events ->
            if(text.isBlank()){
                events
            }
            else{
                events.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _events.value
        )


    private val _categories = MutableStateFlow(EventList.typeList)
    val categories = searchText
        .onEach { _isSearching.update { true } }
        .combine(_categories){text,events ->
            if(text.isBlank()){
                events
            }
            else{
                events.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _categories.value
        )

    private val _myEvents = MutableStateFlow(EventList.myEventList)
    val myEvents = searchText
        .onEach { _isSearching.update { true } }
        .combine(_myEvents){text,events ->
            if(text.isBlank()){
                events
            }
            else{
                events.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _myEvents.value
        )



    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

}