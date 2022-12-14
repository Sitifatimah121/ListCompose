package com.jetpackproject.jetmovie

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jetpackproject.jetmovie.view.home.HomeMovie
import com.jetpackproject.jetmovie.navigation.NavigationItem
import com.jetpackproject.jetmovie.navigation.Screen
import com.jetpackproject.jetmovie.ui.theme.JetMovieTheme

@Composable
fun MovieAppNavigation (
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar ={
            if (currentRoute != Screen.DetailMovie.route){
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                HomeMovie(navigateToDetail = { movieId ->
                    navController.navigate(Screen.DetailMovie.createRoute(movieId))
                    }
                )
            }

            composable(Screen.Profil.route){
                ProfileScreen()
            }

            composable(
                route = Screen.DetailMovie.route,
                arguments = listOf(navArgument("movieId"){type = NavType.IntType}),
            ){
                val id = it.arguments?.getInt("movieId") ?: 0
                DetailMovie(
                    movieId = id,
                    navigateToBack = { navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),

            NavigationItem(
                title = "Profile",
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profil
            )
        )

        BottomNavigation() {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                           Icon(
                               imageVector = item.icon,
                               contentDescription = item.title
                           )
                    },
                    label = {Text(item.title)},
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MovieAppNavigationPreview(){
    JetMovieTheme {
        MovieAppNavigation()
    }
}