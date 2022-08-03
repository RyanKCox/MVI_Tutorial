package com.revature.mvitutorial.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.revature.mvitutorial.data.api.ApiHelper
import com.revature.mvitutorial.data.repository.MainRepository
import com.revature.mvitutorial.ui.main.intent.MainIntent
import com.revature.mvitutorial.ui.main.viewstate.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel(
    private val repository: MainRepository
) :ViewModel() {
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }
    private fun handleIntent(){
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{
                when(it){
                    is MainIntent.FetchUser -> fetchUser()
                }
            }
        }
    }
    private fun fetchUser(){
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try{
                MainState.Users(repository.getUsers())
            }catch (e:Exception){
                MainState.Error(e.localizedMessage)
            }
        }
    }
}
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}