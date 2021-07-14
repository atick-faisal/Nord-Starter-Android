package ai.andromeda.nordstarter.ui.home

import ai.andromeda.nordstarter.R
import ai.andromeda.nordstarter.base.ui.BaseFragment
import ai.andromeda.nordstarter.base.ui.BaseViewModel
import ai.andromeda.nordstarter.databinding.FragmentHomeBinding
import ai.andromeda.nordstarter.extensions.hide
import ai.andromeda.nordstarter.extensions.observe
import ai.andromeda.nordstarter.extensions.showToast
import ai.andromeda.nordstarter.ui.home.adapter.ItemAdapter
import ai.andromeda.nordstarter.utils.Resource
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null
    private var loadingProgress: CircularProgressIndicator? = null
    private val itemAdapter: ItemAdapter by lazy {
        ItemAdapter(::onItemClick)
    }

    override val baseViewModel: BaseViewModel
        get() = viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        setHasOptionsMenu(true)

        initializeViews()
        observeLiveData()
    }

    private fun initializeViews() {
        binding?.apply {
            listDummyItem.adapter = itemAdapter
        }
    }

    override fun observeLiveData() {
        super.observeLiveData()

        observe(viewModel.loginStatus) { loginStatus ->
            if (!loginStatus) {
                Timber.d("user not authorized ... ")
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToAuthenticationFragment()
                )
            } else {
                Timber.d("user is authorized ... ")
                binding?.apply {
                    splashView.hide()
                }
            }
        }

        observe(viewModel.items) { result ->
            itemAdapter.submitList(result.data)
            when (result) {
                is Resource.Loading -> {
                    loadingProgress?.show()
                    Timber.d("loading data from server... ")
                }
                is Resource.Error -> {
                    loadingProgress?.hide()
                    Timber.e(result.error, "error loading data from server!")
                }
                is Resource.Success -> {
                    loadingProgress?.hide()
                    Timber.d("data successfully loaded ... ")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_app_bar_menu, menu)
        loadingProgress = menu.findItem(R.id.userInitial).actionView
            .findViewById(R.id.loading_progress)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController()) ||
                super.onOptionsItemSelected(item)
    }

    private fun onItemClick(id: Long) {
        context?.showToast("ID: $id")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("on resume called")
        loadingProgress?.hide()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loadingProgress = null
        binding = null
    }
}