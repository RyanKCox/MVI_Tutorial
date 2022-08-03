package com.revature.mvitutorial.ui.main.viewstate

import com.revature.mvitutorial.data.model.User

sealed class MainState {
    object Idle:MainState()
    object Loading:MainState()
    data class Users(val user:List<User>):MainState()
    data class Error(val error:String?):MainState()
}