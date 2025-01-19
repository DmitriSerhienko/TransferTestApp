package com.ex.transferscreen

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ex.transferscreen.navigation.LocalNavController
import com.ex.transferscreen.navigation.MainNavigation.Dashboard
import com.ex.transferscreen.navigation.MainNavigation.DebitCards
import com.ex.transferscreen.navigation.MainNavigation.FloatingButton
import com.ex.transferscreen.navigation.MainNavigation.Settings
import com.ex.transferscreen.navigation.MainNavigation.Statement
import com.ex.transferscreen.ui.screens.ConstructionScreen
import com.ex.transferscreen.ui.theme.AppThemeCompose
import com.ex.transferscreen.ui.screens.TransferScreen
import com.ex.transferscreen.ui.screens.model.TransferScreenViewModel
import com.ex.transferscreen.ui.compose_elements.FloatingButtonStyled
import com.ex.transferscreen.ui.compose_elements.NavigationBottomBar
import com.ex.transferscreen.ui.compose_elements.TransferTopBar
import org.koin.compose.KoinApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@Composable
fun App() {

    val viewModelModule = module {
        viewModel { TransferScreenViewModel() }
    }

    KoinApplication(
        application = { modules(viewModelModule) }
    ) {
        val mainNavController = rememberNavController()

        AppThemeCompose {
            CompositionLocalProvider(LocalNavController provides mainNavController) {
                Scaffold(
                    modifier = Modifier.systemBarsPadding(),
                    topBar = {
                        TransferTopBar()
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    floatingActionButton = {
                        FloatingButtonStyled()
                    },
                    bottomBar = {
                        NavigationBottomBar()
                    },
                ) { innerPaddings ->

                    NavHost(
                        navController = mainNavController,
                        startDestination = Dashboard.route,
                        modifier = Modifier.fillMaxSize(),
                        enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Left,
                                animationSpec = tween(300)
                            )
                        }
                    ) {

                        composable(Dashboard.route) {
                            TransferScreen(innerPaddings = innerPaddings)
                        }

                        composable(Settings.route) {
                            ConstructionScreen()
                        }

                        composable(Statement.route) {
                            ConstructionScreen()
                        }

                        composable(DebitCards.route) {
                            ConstructionScreen()
                        }

                        composable(FloatingButton.route) {
                            ConstructionScreen()
                        }

                    }
                }
            }

        }

    }

}



