package ai.andromeda.nordstarter.extensions

import ai.andromeda.nordstarter.utils.Event
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

inline fun <T> LifecycleOwner.observe(
    liveData: LiveData<T>, crossinline action: (T) -> Unit
) {
    liveData.observe(this, { it?.let(action) })
}

inline fun <T> LifecycleOwner.observeEvent(
    liveData: LiveData<Event<T>>, crossinline action: (T) -> Unit
) {
    liveData.observe(this, { it?.getContentIfNotHandled()?.let(action) })
}