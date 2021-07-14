package ai.andromeda.nordstarter.ui.home

import ai.andromeda.nordstarter.base.ui.BaseViewModel
import ai.andromeda.nordstarter.data.home.HomeRepository
import ai.andromeda.nordstarter.storage.room.entity.Item
import ai.andromeda.nordstarter.utils.Resource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : BaseViewModel() {

    val loginStatus = repository.getLoginStatus().asLiveData()

    private val _items = MutableLiveData<Resource<List<Item>>>()
    val items: LiveData<Resource<List<Item>>>
        get() = _items

    fun loadItems(numItems: Int) {
        viewModelScope.launch {
            repository.getItems(numItems).collect {
                _items.postValue(it)
            }
        }
    }
}