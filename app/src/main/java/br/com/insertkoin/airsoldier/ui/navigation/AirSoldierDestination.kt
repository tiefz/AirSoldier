package br.com.insertkoin.airsoldier.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AirSoldierDestination {
    val icon: ImageVector
    val route: String
}

object Home : AirSoldierDestination {
    override val icon = Icons.Filled.Home
    override val route = "home"
}

object Game : AirSoldierDestination {
    override val icon = Icons.Filled.NoteAlt
    override val route = "game"
}

object Round : AirSoldierDestination {
    override val icon = Icons.Filled.Paragliding
    override val route = "round"
}

object Profile : AirSoldierDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "profile"
    const val userPictureArg = "userPicture"
    val routeWithArgs = "${route}?{${userPictureArg}}"
    val arguments = listOf(
        navArgument(userPictureArg) {
            type = NavType.StringType
            defaultValue = ""
        }
    )
}

object Success : AirSoldierDestination {
    override val icon = Icons.Filled.Home
    override val route = "success"
}

object Splash : AirSoldierDestination {
    override val icon = Icons.Filled.Home
    override val route = "splash"
}

object Store : AirSoldierDestination {
    override val icon = Icons.Filled.Store
    override val route = "store"
}

val airSoldierTabRowScreens = listOf(Home, Game, Store)
val showNavBars = listOf(Home, Game, Round, Profile, Success, Store)
