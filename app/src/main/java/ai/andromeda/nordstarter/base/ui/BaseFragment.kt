package ai.andromeda.nordstarter.base.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment(
    @LayoutRes layoutRes: Int
) : Fragment(layoutRes) {

    protected var binding: ViewBinding? = null

    open fun observerLiveData() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observerLiveData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    protected fun isInternetAvailable(): Boolean {
        val cm = requireContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null
    }
}