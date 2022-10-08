package br.com.insertkoin.airsoldier.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.insertkoin.airsoldier.ui.navigation.*
import br.com.insertkoin.airsoldier.ui.navigation.screens.GameScreen
import br.com.insertkoin.airsoldier.ui.navigation.screens.StoreScreen
import br.com.insertkoin.airsoldier.ui.theme.AirSoldierTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AirSoldierTheme {
                AirSoldierHome()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun AirSoldierHome(
) {
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

    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        airSoldierTabRowScreens.find { it.route == currentDestination?.route } ?: Home


    val showNavBars = navController
        .currentBackStackEntryAsState().value?.destination?.route in showNavBars.map { it.route }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (showNavBars) {
                AppBar(
                    onNavigationIconClicked = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (showNavBars) {
                AirSoldierTabRow(
                    allScreens = airSoldierTabRowScreens,
                    onTabSelected = { newScreen ->
                        navController.navigateSingleTopTo(newScreen.route)
                    },
                    currentScreen = currentScreen
                )
            }
        },
        drawerContent = {
            HomeDrawer()
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Splash.route,
            modifier = Modifier.padding(innerPadding)
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
        }
    }
}

@Preview
@Composable
fun PreviewAirSoldierApp() {
    MaterialTheme {
        AirSoldierHome()
    }
}