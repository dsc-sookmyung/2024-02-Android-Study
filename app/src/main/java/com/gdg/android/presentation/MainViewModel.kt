package com.gdg.android.presentation

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.android.api.ServicePool
import com.gdg.android.api.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _users = MutableLiveData<List<User>>() // 내부에서 수정 가능한 데이터
    val users: LiveData<List<User>> get() = _users // 외부에서 읽기만 가능한 데이터

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auto_login")
    // 키 생성
    private val AUTO_LOGIN_KEY = booleanPreferencesKey("auto_login")

    // 자동 로그인 상태 저장 함수
    fun saveAutoLoginState(context: Context, isLoggedIn: Boolean) {
        viewModelScope.launch {
            context.dataStore.edit { preferences ->
                preferences[AUTO_LOGIN_KEY] = isLoggedIn
            }
        }
    }

    // 자동 로그인 상태 불러오기 함수
    fun getAutoLoginState(context: Context): Flow<Boolean> {
        return context.dataStore.data
            .map { preferences ->
                preferences[AUTO_LOGIN_KEY] ?: false // 기본값은 false
            }
    }

    fun getUsers() {
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