package ai.andromeda.nordstarter.ui.logout

import ai.andromeda.nordstarter.R
import ai.andromeda.nordstarter.base.ui.BaseFragment
import ai.andromeda.nordstarter.base.ui.BaseViewModel
import ai.andromeda.nordstarter.databinding.FragmentLogoutBinding
import ai.andromeda.nordstarter.extensions.observeEvent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogoutFragment : BaseFragment(R.layout.fragment_logout) {

    private val viewModel: LogoutViewModel by viewModels()
    private var binding: FragmentLogoutBinding? = null

    override val baseViewModel: BaseViewModel
        get() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLogoutBinding.bind(view)
        observeLiveData()
    }

    override fun observeLiveData() {
        super.observeLiveData()

        observeEvent(viewModel.isLogoutComplete) { isLogoutComplete ->
            if (isLogoutComplete) {
                findNavController().navigate(
                    LogoutFragmentDirections.actionLogoutFragmentToAuthenticationFragment()
                )
            }
        }
    }
}