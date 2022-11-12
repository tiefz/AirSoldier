package br.com.insertkoin.airsoldier.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.insertkoin.airsoldier.data.models.Game
import br.com.insertkoin.airsoldier.data.models.LoadOut
import br.com.insertkoin.airsoldier.data.models.Round
import br.com.insertkoin.airsoldier.data.models.User
import br.com.insertkoin.airsoldier.repository.AirSoldierRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val airSoldierRepository: AirSoldierRepository) :
    ViewModel() {

    private val loadOuts = listOf("Assalto", "Suporte", "DMR", "Sniper")
    private val _user = MutableLiveData<User>()
    private val _gameList = MutableLiveData<List<Game>>()
    private val _roundList = MutableLiveData<List<Round>>()
    private val _loadOutList = MutableLiveData<List<LoadOut>>()
    private val _game = MutableLiveData<Game>()

    init {
        viewModelScope.launch {
            _gameList.value = airSoldierRepository.getGames() ?: return@launch
            _roundList.value = airSoldierRepository.getRounds() ?: return@launch
            _loadOutList.value = airSoldierRepository.getLoadOuts() ?: return@launch

            if (_loadOutList.value.isNullOrEmpty()) {
                generateLoadOuts()
            }
        }
    }

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

    fun generateLoadOuts() {
        viewModelScope.launch {
            loadOuts.forEach { loadOut ->
                airSoldierRepository.insertLoadOut(
                    LoadOut(
                        name = loadOut,
                        experience = 1,
                        level = 1
                    )
                )
            }
        }
    }

    fun startGame(name: String) {
        viewModelScope.launch {
            airSoldierRepository.insertGame(
                Game(
                    name = name,
                    date = Date(),
                    kills = 0,
                    deaths = 0,
                    isFinished = false
                )
            )
        }
    }

    fun finishGame(game: Game) {
        viewModelScope.launch {
            airSoldierRepository.updateGame(game)
        }
    }

    fun getGame(gameId: Int) {
        viewModelScope.launch {
            _game.value = airSoldierRepository.getGame(gameId) ?: return@launch
        }
    }

    fun startRound(loadOut: Int) {
        viewModelScope.launch {
            airSoldierRepository.insertRound(
                Round(
                    kills = 0,
                    isDead = false,
                    isTeamWinner = false,
                    loadOut = loadOut,
                    isFinished = false
                )
            )
        }
    }

    val user: LiveData<User>
        get() = _user

    val gameList: LiveData<List<Game>>
        get() = _gameList

    val currentGame: LiveData<Game>
        get() = _game

    val roundList: LiveData<List<Round>>
        get() = _roundList

    val loadOutList: LiveData<List<LoadOut>>
        get() = _loadOutList
}