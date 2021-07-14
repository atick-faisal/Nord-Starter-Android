package ai.andromeda.nordstarter.ui.authentication

import ai.andromeda.nordstarter.R
import ai.andromeda.nordstarter.base.ui.BaseFragment
import ai.andromeda.nordstarter.base.ui.BaseViewModel
import ai.andromeda.nordstarter.databinding.FragmentAuthenticationBinding
import ai.andromeda.nordstarter.extensions.*
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class AuthenticationFragment : BaseFragment(R.layout.fragment_authentication) {

    private val viewModel: AuthenticationViewModel by viewModels()
    private var binding: FragmentAuthenticationBinding? = null

    override val baseViewModel: BaseViewModel
        get() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAuthenticationBinding.bind(view)

        initializeViews()
        observeLiveData()
    }

    private fun initializeViews() {
        binding?.apply {
            userTypeButton.setOnClickListener { toggleAuthenticationType() }
            authenticationButton.setOnClickListener { authenticate() }
        }
    }

    override fun observeLiveData() {
        super.observeLiveData()

        observeEvent(viewModel.login) { response ->
            if (response.success) {
                Timber.d("successfully logged in ...")
                viewModel.saveLoginStatus(true)
            } else {
                Timber.d("login failed!")
                viewModel.saveLoginStatus(false)
            }
        }

        observeEvent(viewModel.register) { response ->
            if (response.success) {
                Timber.d("successfully logged in ...")
                viewModel.saveLoginStatus(true)
            } else {
                Timber.d("login failed!")
                viewModel.saveLoginStatus(false)
            }
        }

        observeEvent(viewModel.isLoginStatusSaved) { isLoginStatusSaved ->
            if (isLoginStatusSaved) {
                Timber.d("login session saved ... ")
                findNavController().navigate(
                    AuthenticationFragmentDirections.actionAuthenticationFragmentToHomeFragment()
                )
            }
        }
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
            context?.showToast(getString(R.string.no_internet))
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