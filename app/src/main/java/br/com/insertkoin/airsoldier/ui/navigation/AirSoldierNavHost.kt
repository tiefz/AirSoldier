package br.com.insertkoin.airsoldier.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.insertkoin.airsoldier.ui.navigation.screens.GameScreen
import br.com.insertkoin.airsoldier.ui.navigation.screens.ProfileScreen
import br.com.insertkoin.airsoldier.ui.navigation.screens.StoreScreen

@Composable
fun AirSoldierNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    updateUserPicture: (String) -> Unit,
    saveButton: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Splash.route,
        modifier = modifier
    ) {
        composable(route = Splash.route) {
            SplashScreenAirSoldier(navController)
        }
        composable(route = Home.route) {
            HomeScreen()
        }
        composable(route = Game.route) {
            GameScreen()
        }
        composable(route = Store.route) {
            StoreScreen()
        }
        composable(
            route = Profile.routeWithArgs,
            arguments = Profile.arguments
        ) { navBackStackEntry ->
            val userPicture =
                navBackStackEntry.arguments?.getString(Profile.userPictureArg)
            val userName =
                navBackStackEntry.arguments?.getString(Profile.userNameArg)
            ProfileScreen(
                saveButton = saveButton,
                updateUserPicture = updateUserPicture,
                userName = userName.toString(),
                userPicture = userPicture.toString()
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

fun NavHostController.navigateToEditProfile(userPictureArg: String, userNameArg: String) {
    this.navigateSingleTopTo("${Profile.route}?$userPictureArg/$userNameArg")
}