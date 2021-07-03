package ai.andromeda.nordstarter

import ai.andromeda.nordstarter.data.dummy.DummyRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    repository: DummyRepository
) : ViewModel() {
    val items = repository.getItems().asLiveData()
}