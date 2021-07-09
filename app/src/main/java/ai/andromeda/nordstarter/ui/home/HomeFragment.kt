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
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null
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
            when (result) {
                is Resource.Loading -> Log.i(LOG_TAG, "LOADING ... ")
                is Resource.Error -> Log.i(LOG_TAG, "ERROR ... " + result.error)
                is Resource.Success -> {
                    itemAdapter.submitList(result.data)
                    Log.i(LOG_TAG, "SUCCESS ... " + result.data)
                }
            }
        })

        viewModel.toastMessage.observe(this, { message ->
            context?.showToast(message)
        })
    }

    private fun onItemClick(id: Long) {
        viewModel.toastMessage.value = "ID: $id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}