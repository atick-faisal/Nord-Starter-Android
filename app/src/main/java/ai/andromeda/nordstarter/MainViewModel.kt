package ai.andromeda.nordstarter

import ai.andromeda.nordstarter.base.ui.BaseViewModel
import ai.andromeda.nordstarter.data.dummy.DummyRepository
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: DummyRepository
) : BaseViewModel() {
    val items = repository.getItems().asLiveData()
}