package com.example.bottomnavigationbar.view.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bottomnavigationbar.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState()?.value?.destination?.route

    NavigationBar(containerColor = Color.White) {
        listOf(Screen.Home, Screen.Cart, Screen.History, Screen.Profile).forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.title
                    )
                },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route){
                            popUpTo(navController.graph.startDestinationId) { saveState = true}
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}
