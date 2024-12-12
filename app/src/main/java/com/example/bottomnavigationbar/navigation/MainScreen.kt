package com.example.bottomnavigationbar.navigation

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigationbar.MainActivity
import com.example.bottomnavigationbar.Screen
import com.example.bottomnavigationbar.view.component.BottomNavigationBar
import com.example.bottomnavigationbar.view.screens.CartScreen
import com.example.bottomnavigationbar.view.screens.HistoryScreen
import com.example.bottomnavigationbar.view.screens.HomeScreen
import com.example.bottomnavigationbar.view.screens.ItemsScreen
import com.example.bottomnavigationbar.view.screens.ProfileScreen
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState()?.value?.destination?.route
    var backPressed by remember{mutableStateOf(false)}
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var resetBackPressed by remember {mutableStateOf<Job?>(null)}

    BackHandler( enabled = currentRoute == Screen.Home.route) {
        if (backPressed) {
            resetBackPressed?.cancel()
            (context as Activity).finish()
        } else {
            backPressed = true
            Toast.makeText(context, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()

            resetBackPressed = coroutineScope.launch {
                kotlinx.coroutines.delay(3000)
                backPressed = false
            }
        }
    }
    
    Scaffold(
        bottomBar = {
            if (currentRoute in listOf(Screen.Home.route, Screen.Cart.route, Screen.History.route, Screen.Profile.route))
                BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navController
                )
            }
            composable(Screen.Cart.route) {
                CartScreen()
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Items.route) {
                ItemsScreen(
                    navController = navController
                )
            }
        }
    }
}
