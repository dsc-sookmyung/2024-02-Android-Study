package com.gdg.android.presentation.main

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
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auto_login")

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

    companion object {
        private val AUTO_LOGIN_KEY = booleanPreferencesKey("auto_login")
    }
}