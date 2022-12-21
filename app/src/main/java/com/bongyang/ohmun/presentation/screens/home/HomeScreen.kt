package com.bongyang.ohmun.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.bongyang.ohmun.navigation.Screen
import com.bongyang.ohmun.presentation.screens.common.ListContent
import com.bongyang.ohmun.presentation.screens.common.OhmunDrawer
import com.bongyang.ohmun.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allMovies = homeViewModel.getAllMovies.collectAsLazyPagingItems()
    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.statusBarColor

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        },
        drawerContent = {
            OhmunDrawer()
        },
        content = {
            ListContent(
                movies = allMovies,
                navController = navController
            )
        }
    )
}

