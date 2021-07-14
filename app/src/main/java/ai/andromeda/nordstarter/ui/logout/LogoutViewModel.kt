package ai.andromeda.nordstarter.ui.logout

import ai.andromeda.nordstarter.base.ui.BaseViewModel
import ai.andromeda.nordstarter.data.authentication.AuthenticationRepository
import ai.andromeda.nordstarter.utils.Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoutViewModel @Inject constructor(
    authenticationRepository: AuthenticationRepository
) : BaseViewModel() {

    private val _isLogoutComplete = MutableLiveData<Event<Boolean>>()
    val isLogoutComplete: LiveData<Event<Boolean>>
        get() = _isLogoutComplete

    init {
        viewModelScope.launch {
            authenticationRepository.saveLoginStatus(false)
            _isLogoutComplete.value = Event(true)
        }
    }

}