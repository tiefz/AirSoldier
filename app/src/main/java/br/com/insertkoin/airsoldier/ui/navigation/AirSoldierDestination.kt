package br.com.insertkoin.airsoldier.ui.navigation

interface AirSoldierDestination {
    val route: String
}

object Home : AirSoldierDestination {
    override val route = "home"
}

object Game : AirSoldierDestination {
    override val route = "game"
}

object Round : AirSoldierDestination {
    override val route = "round"
}

object Profile : AirSoldierDestination {
    override val route = "profile"
}

object Success : AirSoldierDestination {
    override val route = "success"
}

object Splash : AirSoldierDestination {
    override val route = "splash"
}

val airSoldierTabRowScreens = listOf(Home, Game, Round, Profile, Success, Splash)
