package ai.andromeda.nordstarter.base.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val loader = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()

    fun handleException(throwable: Throwable) {
        toastMessage.value = throwable.message.toString()
    }
}