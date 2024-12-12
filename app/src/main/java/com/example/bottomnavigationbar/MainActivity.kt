package com.example.bottomnavigationbar

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.bottomnavigationbar.navigation.MainScreen
import com.example.bottomnavigationbar.ui.theme.BottomNavigationBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            BottomNavigationBarTheme {
                 MainScreen()
//            }
        }
    }
}

sealed class Screen(
    val route: String,
    val title: String,
    val icon: Int
) {
    data object Home : Screen("home","Home", R.drawable.ic_home)
    data object Cart: Screen( "cart","Cart", R.drawable.ic_cart)
    data object History: Screen( "history","History", R.drawable.ic_history)
    data object Profile: Screen( "profile","Profile", R.drawable.ic_profile)
}



