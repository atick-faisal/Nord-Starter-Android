package ai.andromeda.nordstarter.ui.authentication

import ai.andromeda.nordstarter.base.ui.BaseViewModel
import ai.andromeda.nordstarter.data.authentication.AuthenticationRepository
import ai.andromeda.nordstarter.data.authentication.model.AuthenticationResponse
import ai.andromeda.nordstarter.data.authentication.model.LoginBody
import ai.andromeda.nordstarter.data.authentication.model.RegisterBody
import ai.andromeda.nordstarter.utils.Event
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

    private val _login = MutableLiveData<Event<AuthenticationResponse>>()
    val login: LiveData<Event<AuthenticationResponse>>
        get() = _login

    private val _register = MutableLiveData<Event<AuthenticationResponse>>()
    val register: LiveData<Event<AuthenticationResponse>>
        get() = _register

    private val _isLoginStatusSaved: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isLoginStatusSaved: LiveData<Event<Boolean>>
        get() = _isLoginStatusSaved

    fun login(
        email: String,
        password: String
    ) {
        // TODO: Implement proper login logic
        _login.value = Event(AuthenticationResponse(true))

        if (isEmailValid(email)) {
            viewModelScope.launch {
                _login.value = Event(
                    authenticationRepository.login(
                        LoginBody(
                            email = email,
                            password = password
                        )
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
        _register.value = Event(AuthenticationResponse(true))

        if (isEmailValid(email)) {
            viewModelScope.launch {
                _register.value = Event(
                    authenticationRepository.register(
                        RegisterBody(
                            name = name,
                            email = email,
                            password = password
                        )
                    )
                )
            }
        }
    }

    fun saveLoginStatus(loginStatus: Boolean) {
        viewModelScope.launch {
            authenticationRepository.saveLoginStatus(loginStatus)
            _isLoginStatusSaved.value = Event(true)
        }
    }
}