package com.gdg.android.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.android.api.ServicePool
import com.gdg.android.api.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            runCatching { ServicePool.userService.getUsers(page = 2) }
                .onSuccess {
                    _users.value = it.data
                    Log.d("MainViewModel", "getUsers: ${it.data}")
                }
                .onFailure {
                    _users.value = emptyList()
                    Log.e("MainViewModel", "getUsers: ${it.message}")
                }
        }
    }
}