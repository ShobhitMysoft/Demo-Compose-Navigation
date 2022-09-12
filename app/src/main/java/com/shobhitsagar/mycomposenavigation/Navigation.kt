package com.shobhitsagar.mycomposenavigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screens.DetailScreen.route + "/{$NAME_KEY}", arguments = listOf(
            navArgument(NAME_KEY) {
                type = NavType.StringType
                defaultValue = "World"
                nullable = true
            }
        )) { entry ->
            DetailScreen(name = entry.arguments?.getString(NAME_KEY))
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
            text = it
        }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            navController.navigate(route = Screens.DetailScreen.withArgs(text))
        }) {
            Text(text = "Go to Detail Screen")
        }
    }
}

@Composable
fun DetailScreen(name: String?) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Hello $name!")
    }
}