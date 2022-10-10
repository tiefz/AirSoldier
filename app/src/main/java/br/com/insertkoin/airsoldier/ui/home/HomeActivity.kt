package br.com.insertkoin.airsoldier.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.insertkoin.airsoldier.data.models.User
import br.com.insertkoin.airsoldier.ui.navigation.*
import br.com.insertkoin.airsoldier.ui.theme.AirSoldierTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeViewModel: HomeViewModel by viewModels()
        setContent {
            AirSoldierTheme {
                AirSoldierHome(homeViewModel)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun AirSoldierHome(
    viewModel: HomeViewModel
) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        airSoldierTabRowScreens.find { it.route == currentDestination?.route } ?: Home
    val user = viewModel.user.value ?: User(
        id = 1,
        name = "Usuário",
        experience = 0,
        level = 1,
        avatar = 1
    )
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
            HomeDrawer(
                modifier = Modifier,
                user
            )
        }
    ) { innerPadding ->
        AirSoldierNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}