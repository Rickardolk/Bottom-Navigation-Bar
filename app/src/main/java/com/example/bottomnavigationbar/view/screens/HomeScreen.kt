package com.example.bottomnavigationbar.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bottomnavigationbar.Screen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Box(
        modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Home Screen",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = { navController.navigate(Screen.Items.route) }
        ) {
            Text(
                text = "Masuk ke ItemsScreen"
            )
        }
    }
}