package ai.andromeda.nordstarter.base.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected val loader = MutableLiveData<Boolean>()
    protected val toastMessage = MutableLiveData<String>()

    fun handleException(throwable: Throwable) {
        toastMessage.value = throwable.message.toString()
    }
}