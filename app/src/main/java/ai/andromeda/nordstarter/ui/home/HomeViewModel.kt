package ai.andromeda.nordstarter.ui.home

import ai.andromeda.nordstarter.base.ui.BaseViewModel
import ai.andromeda.nordstarter.data.home.HomeRepository
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(repository: HomeRepository) : BaseViewModel() {
    val items = repository.getItems(30).asLiveData()
}