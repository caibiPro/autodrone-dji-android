package com.mingqing.autodrone

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mingqing.autodrone.ui.theme.AutoDroneTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AutoDroneTheme {
               MainScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            CustomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = "home", Modifier.padding(innerPadding)) {
            composable("home") { Greeting(name = "Home") }
            composable("profile") { Greeting(name = "Profile") }
            composable("settings") { Greeting(name = "Settings") }
            composable("about") { Greeting(name = "About") }
        }
    }
}

@Composable
fun CustomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            selected = navController.currentBackStackEntry?.destination?.route == "home",
            onClick = { navController.navigate("home") },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = navController.currentBackStackEntry?.destination?.route == "profile",
            onClick = { navController.navigate("profile") },
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
        NavigationBarItem(
            selected = navController.currentBackStackEntry?.destination?.route == "settings",
            onClick = { navController.navigate("settings") },
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings") },
            label = { Text("Settings") }
        )
        NavigationBarItem(
            selected = navController.currentBackStackEntry?.destination?.route == "about",
            onClick = { navController.navigate("about") },
            icon = { Icon(imageVector = Icons.Default.Info, contentDescription = "About") },
            label = { Text("About") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AutoDroneTheme {
        Greeting("Android")
    }
}