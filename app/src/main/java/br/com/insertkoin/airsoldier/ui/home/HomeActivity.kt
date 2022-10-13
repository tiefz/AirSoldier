package br.com.insertkoin.airsoldier.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.insertkoin.airsoldier.data.models.User
import br.com.insertkoin.airsoldier.ui.navigation.*
import br.com.insertkoin.airsoldier.ui.theme.AirSoldierTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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

@Composable
private fun AirSoldierHome(
    viewModel: HomeViewModel
) {
    viewModel.getUser()
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination
    val currentScreen =
        airSoldierTabRowScreens.find { it.route == currentDestination?.route } ?: Home
    if (viewModel.user.value == null) {
        viewModel.generateUser()
    }
    val userData = viewModel.user.observeAsState()
    val user = userData.value ?: User(
        id = 1,
        name = "Usuário",
        experience = 0,
        level = 1,
        picture = ""
    )
    val hideNavBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in hideNavBar.map { it.route }
    val updateUserPicture: (String) -> Unit = { viewModel.updateUserPicture(user, it) }
    val saveButton: (String) -> Unit = {
        viewModel.updateUserName(user, it)
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (!hideNavBar) {
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
            if (!hideNavBar) {
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
            val encodedUrl = URLEncoder.encode(user.picture, StandardCharsets.UTF_8.toString())
            HomeDrawer(
                modifier = Modifier,
                user,
                editProfile = {
                    navController.navigateToEditProfile(encodedUrl, user.name)
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
            )
        }
    ) { innerPadding ->
        AirSoldierNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            updateUserPicture = updateUserPicture,
            saveButton = saveButton
        )
    }
}