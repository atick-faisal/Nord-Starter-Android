package ai.andromeda.nordstarter.ui.authentication

import ai.andromeda.nordstarter.R
import ai.andromeda.nordstarter.base.ui.BaseFragment
import ai.andromeda.nordstarter.databinding.FragmentAuthenticationBinding
import ai.andromeda.nordstarter.extensions.getValue
import ai.andromeda.nordstarter.extensions.hide
import ai.andromeda.nordstarter.extensions.show
import ai.andromeda.nordstarter.extensions.showToast
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationFragment : BaseFragment(R.layout.fragment_authentication) {

    private val viewModel: AuthenticationViewModel by viewModels()
    private var binding: FragmentAuthenticationBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthenticationBinding.bind(view)

        binding?.apply {
            userTypeButton.setOnClickListener { toggleAuthenticationType() }
            authenticationButton.setOnClickListener { authenticate() }
        }
    }

    override fun observerLiveData() {
        viewModel.login.observe(this, { response ->
            response?.let {
                if (it.success) viewModel.saveLoginStatus(true)
                else viewModel.saveLoginStatus(false)
            }
        })

        viewModel.register.observe(this, { response ->
            response?.let {
                if (it.success) viewModel.saveLoginStatus(true)
                else viewModel.saveLoginStatus(false)
            }
        })

        viewModel.isLoginStatusSaved.observe(this, { isLoginStatusSaved ->
            if (isLoginStatusSaved) {
                findNavController().navigate(
                    AuthenticationFragmentDirections.actionAuthenticationFragmentToHomeFragment()
                )
            }
        })

        viewModel.toastMessage.observe(this, { message ->
            context?.showToast(message)
        })
    }

    private fun authenticate() {
        if (isInternetAvailable()) {
            if (viewModel.alreadyUser) {
                binding?.apply {
                    viewModel.register(
                        nameEditText.getValue(),
                        emailEditText.getValue(),
                        passwordEditText.getValue()
                    )
                }
            } else {
                binding?.apply {
                    viewModel.login(
                        emailEditText.getValue(),
                        passwordEditText.getValue()
                    )
                }
            }
        } else {
            viewModel.toastMessage.value = getString(R.string.no_internet)
        }
    }

    private fun toggleAuthenticationType() {
        if (viewModel.alreadyUser) {
            binding?.apply {
                userTypeButton.text = getString(R.string.already_a_user)
                authenticationTypeText.text = getString(R.string.sign_up)
                authenticationButton.text = getString(R.string.sign_up)
                nameEditLayout.show()
            }
        } else {
            binding?.apply {
                userTypeButton.text = getString(R.string.not_a_user)
                authenticationTypeText.text = getString(R.string.log_in)
                authenticationButton.text = getString(R.string.log_in)
                nameEditLayout.hide()
            }
        }
        viewModel.alreadyUser = !viewModel.alreadyUser
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}