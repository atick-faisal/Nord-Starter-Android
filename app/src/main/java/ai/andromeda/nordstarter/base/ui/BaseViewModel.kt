package ai.andromeda.nordstarter.base.ui

import ai.andromeda.nordstarter.utils.Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    private val _loader = MutableLiveData<Event<Boolean>>()
    val loader: LiveData<Event<Boolean>>
        get() = _loader

    private val _toastMessage = MutableLiveData<Event<String>>()
    val toastMessage: LiveData<Event<String>>
        get() = _toastMessage

}