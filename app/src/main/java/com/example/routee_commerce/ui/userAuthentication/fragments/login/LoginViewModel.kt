package com.example.routee_commerce.ui.userAuthentication.fragments.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.data.model.request.LoginRequest
import com.example.routee_commerce.domain.repos.AuthRepo
import com.example.routee_commerce.domain.utils.ApiResult
import com.example.routee_commerce.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepo: AuthRepo) : ViewModel() {
    private val _loginState = MutableLiveData<Resource<Boolean>>()
    val loginState: LiveData<Resource<Boolean>> = _loginState

    var email = MutableLiveData<String>("ahmednabil@test.com")
    var password = MutableLiveData<String>("ahmed@123")
    var emailError: MutableLiveData<String?> = MutableLiveData<String?>()
    var passwordError: MutableLiveData<String?> = MutableLiveData<String?>()
    fun login() {
        if (!isValid()) return
        _loginState.value = Resource.Loading
        viewModelScope.launch {
            //todo: get email and password from edit text
            when (val result = authRepo.
            login(LoginRequest(email = email.value, password = password.value))) {
                is ApiResult.Error -> _loginState.value = Resource.Error(result.error)
                is ApiResult.Success -> _loginState.value = Resource.Success(result.data)
            }
        }

    }

    private fun isValid(): Boolean {
        if (email.value.isNullOrEmpty()) {
            emailError.value = "Email can not be empty"
            return false
        } else {
            emailError.value = null
        }
        if (password.value.isNullOrEmpty()) {
            passwordError.value = "Password can not be empty"
            return false
        } else {
            passwordError.value = null
        }
        if (password.value!!.length < 6) {
            passwordError.value = "Passwords  must be atleast 6 chars"
            return false
        } else {
            passwordError.value = null
        }
        return true
    }
}