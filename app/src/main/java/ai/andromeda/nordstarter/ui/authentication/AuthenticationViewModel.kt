package ai.andromeda.nordstarter.ui.authentication

import ai.andromeda.nordstarter.base.ui.BaseViewModel
import ai.andromeda.nordstarter.data.authentication.AuthenticationRepository
import ai.andromeda.nordstarter.data.authentication.model.AuthenticationResponse
import ai.andromeda.nordstarter.data.authentication.model.LoginBody
import ai.andromeda.nordstarter.data.authentication.model.RegisterBody
import ai.andromeda.nordstarter.utils.isEmailValid
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : BaseViewModel() {

    var alreadyUser: Boolean = false

    private val _login: MutableLiveData<AuthenticationResponse> = MutableLiveData()
    val login: LiveData<AuthenticationResponse>
        get() = _login

    private val _register: MutableLiveData<AuthenticationResponse> = MutableLiveData()
    val register: LiveData<AuthenticationResponse>
        get() = _register

    private val _isLoginStatusSaved: MutableLiveData<Boolean> = MutableLiveData()
    val isLoginStatusSaved: LiveData<Boolean>
        get() = _isLoginStatusSaved

    fun login(
        email: String,
        password: String
    ) {
        // TODO: Implement proper login logic
        _login.value = AuthenticationResponse(true)

        if (isEmailValid(email)) {
            viewModelScope.launch {
                _login.value = authenticationRepository.login(
                    LoginBody(
                        email = email,
                        password = password
                    )
                )
            }
        }
    }

    fun register(
        name: String,
        email: String,
        password: String
    ) {
        // TODO: Implement proper registration logic
        _register.value = AuthenticationResponse(true)

        if (isEmailValid(email)) {
            viewModelScope.launch {
                _register.value = authenticationRepository.register(
                    RegisterBody(
                        name = name,
                        email = email,
                        password = password
                    )
                )
            }
        }
    }

    fun saveLoginStatus(loginStatus: Boolean) {
        viewModelScope.launch {
            authenticationRepository.saveLoginStatus(loginStatus)
            _isLoginStatusSaved.value = true
        }
    }
}