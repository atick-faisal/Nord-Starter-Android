package ai.andromeda.nordstarter.base.ui

import ai.andromeda.nordstarter.extensions.observeEvent
import ai.andromeda.nordstarter.extensions.showToast
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(
    @LayoutRes layoutRes: Int
) : Fragment(layoutRes) {

    abstract val baseViewModel: BaseViewModel

    protected open fun observeLiveData() {
        observeEvent(baseViewModel.toastMessage) { message ->
            context?.showToast(message)
        }
    }

    protected fun isInternetAvailable(): Boolean {
        val cm = requireContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null
    }
}