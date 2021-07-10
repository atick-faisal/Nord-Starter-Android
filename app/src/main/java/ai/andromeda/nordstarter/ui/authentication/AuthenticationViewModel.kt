package ai.andromeda.nordstarter.ui.authentication

import ai.andromeda.nordstarter.base.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor() : BaseViewModel() {
    var alreadyUser: Boolean = false
}