package com.handson.carttokengenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.handson.carttokengenerator.ui.theme.CartTokenGeneratorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CartTokenGeneratorTheme {
                AppNavigation()
            }
        }
    }
}

//Need to add this to the main activity to enable edge to edge
// Welcome page for the app to register the user
// Then after the user is registered, the app will show the token Number in  new page with a Back button in top bar
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomePage(
                onNavigateToTokenPage = { tokenNumber ->
                    navController.navigate("token/$tokenNumber")
                }
            )
        }
        composable(
            route = "token/{tokenNumber}",
            arguments = listOf(navArgument("tokenNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            val tokenNumber = backStackEntry.arguments?.getInt("tokenNumber") ?: 0
            TokenPage(
                tokenNumber = tokenNumber,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}