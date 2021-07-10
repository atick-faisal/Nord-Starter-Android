package ai.andromeda.nordstarter.ui.authentication

import ai.andromeda.nordstarter.R
import ai.andromeda.nordstarter.base.ui.BaseFragment
import ai.andromeda.nordstarter.databinding.FragmentAuthenticationBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels

class AuthenticationFragment : BaseFragment(R.layout.fragment_authentication) {

    private val viewModel: AuthenticationViewModel by viewModels()
    private var binding: FragmentAuthenticationBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthenticationBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}