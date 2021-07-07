package ai.andromeda.nordstarter.ui.home

import ai.andromeda.nordstarter.R
import ai.andromeda.nordstarter.base.ui.BaseFragment
import ai.andromeda.nordstarter.databinding.HomeFragmentBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels

class HomeFragment : BaseFragment(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)
    }

}