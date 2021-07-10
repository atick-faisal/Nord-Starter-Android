package ai.andromeda.nordstarter.ui.home

import ai.andromeda.nordstarter.R
import ai.andromeda.nordstarter.base.ui.BaseFragment
import ai.andromeda.nordstarter.databinding.FragmentHomeBinding
import ai.andromeda.nordstarter.extensions.showToast
import ai.andromeda.nordstarter.ui.home.adapter.ItemAdapter
import ai.andromeda.nordstarter.utils.LOG_TAG
import ai.andromeda.nordstarter.utils.Resource
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null
    private var loadingProgress: CircularProgressIndicator? = null
    private val itemAdapter: ItemAdapter by lazy {
        ItemAdapter(::onItemClick)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding?.apply {
            listDummyItem.adapter = itemAdapter
        }
    }

    override fun observerLiveData() {
        viewModel.items.observe(this, { result ->
            itemAdapter.submitList(result.data)
            when (result) {
                is Resource.Loading -> {
                    loadingProgress?.show()
                    Log.i(LOG_TAG, "LOADING ... ")
                }
                is Resource.Error -> {
                    loadingProgress?.hide()
                    Log.i(LOG_TAG, "ERROR ... " + result.error)
                }
                is Resource.Success -> {
                    loadingProgress?.hide()
                    Log.i(LOG_TAG, "SUCCESS ... " + result.data?.size)
                }
            }
        })

        viewModel.toastMessage.observe(this, { message ->
            context?.showToast(message)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        loadingProgress = menu.findItem(R.id.userInitial).actionView
            .findViewById(R.id.loading_progress)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun onItemClick(id: Long) {
        viewModel.toastMessage.value = "ID: $id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        loadingProgress = null
        binding = null
    }
}