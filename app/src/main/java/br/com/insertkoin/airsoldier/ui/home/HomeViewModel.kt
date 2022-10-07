package br.com.insertkoin.airsoldier.ui.home

import androidx.lifecycle.ViewModel
import br.com.insertkoin.airsoldier.repository.AirSoldierRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(ainSoldierRepository: AirSoldierRepository) : ViewModel() {
}