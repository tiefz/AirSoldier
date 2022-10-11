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
class HomeViewModel @Inject constructor(private val airSoldierRepository: AirSoldierRepository) :
    ViewModel() {

    private val _user = MutableLiveData<User>()

    fun getUser() {
        viewModelScope.launch {
            _user.value = airSoldierRepository.getUser() ?: return@launch
        }
    }

    fun updateUserName(user: User, userName: String) {
        viewModelScope.launch {
            airSoldierRepository.updateUser(
                User(
                    id = 1,
                    name = userName,
                    experience = user.experience,
                    level = user.level,
                    picture = user.picture
                )
            )
        }
    }

    fun updateUserPicture(user: User, userPicture: String) {
        viewModelScope.launch {
            airSoldierRepository.updateUser(
                User(
                    id = 1,
                    name = user.name,
                    experience = user.experience,
                    level = user.level,
                    picture = userPicture
                )
            )
        }
    }

    fun generateUser() {
        viewModelScope.launch {
            airSoldierRepository.insertUser(
                User(
                    name = "Usuário",
                    experience = 0,
                    level = 1,
                    picture = ""
                )
            )
        }
    }

    val user: LiveData<User>
        get() = _user
}