package br.com.insertkoin.airsoldier.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.insertkoin.airsoldier.data.models.User
import br.com.insertkoin.airsoldier.repository.AirSoldierRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(airSoldierRepository: AirSoldierRepository) : ViewModel() {

    private val _user = MutableLiveData<User?>()

    init {
        viewModelScope.launch {
            airSoldierRepository.insertUser(
                User(
                    id = 1,
                    name = "Tief",
                    experience = 0,
                    level = 3,
                    avatar = 1
                )
            )
            _user.value = airSoldierRepository.getUser() ?: return@launch
        }
    }

    val user: LiveData<User?>
        get() = _user
}