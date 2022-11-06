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
    override val route = "Home"
}

object Game : AirSoldierDestination {
    override val icon = Icons.Filled.NoteAlt
    override val route = "Jogos"
}

object Round : AirSoldierDestination {
    override val icon = Icons.Filled.Paragliding
    override val route = "round"
    const val gameIdArg = "gameId"
    val routeWithArgs = "${route}/{${gameIdArg}}"
    val arguments = listOf(
        navArgument(gameIdArg) {
            type = NavType.IntType
            defaultValue = 0
        }
    )
}

object Profile : AirSoldierDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "profile"
    const val userPictureArg = "userPicture"
    const val userNameArg = "userName"
    val routeWithArgs = "${route}?{${userPictureArg}}/{${userNameArg}}"
    val arguments = listOf(
        navArgument(userPictureArg) {
            type = NavType.StringType
            defaultValue = ""
        },
        navArgument(userNameArg) {
            type = NavType.StringType
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
    override val route = "Loja"
}

val airSoldierTabRowScreens = listOf(Home, Game, Store)
val hideNavBar = listOf(Splash)
