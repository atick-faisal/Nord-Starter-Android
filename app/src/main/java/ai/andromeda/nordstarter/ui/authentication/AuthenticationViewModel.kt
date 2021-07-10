package ai.andromeda.nordstarter.ui.authentication

import ai.andromeda.nordstarter.base.ui.BaseViewModel
import ai.andromeda.nordstarter.data.authentication.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : BaseViewModel() {
    var alreadyUser: Boolean = false

    fun login() {}
    fun register() {}
}