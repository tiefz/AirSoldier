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
import br.com.insertkoin.airsoldier.ui.navigation.Splash
import br.com.insertkoin.airsoldier.ui.navigation.SplashScreenAirSoldier
import br.com.insertkoin.airsoldier.ui.navigation.airSoldierTabRowScreens
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
        airSoldierTabRowScreens.find { it.route == currentDestination?.route } ?: Splash


    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClicked = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
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
                SplashScreenAirSoldier()
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