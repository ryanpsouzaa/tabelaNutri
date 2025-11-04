package com.app.tabelanutricional

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
import com.app.tabelanutricional.model.mock.mockHealthyRecipes
import com.app.tabelanutricional.ui.screen.healthy_recipe_details.HealthyRecipeDetailsScreen
import com.app.tabelanutricional.ui.screen.home.HomeScreen
import com.app.tabelanutricional.ui.theme.LocalSizing
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TabelaNutricionalTheme {
                val sizing = LocalSizing.current

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HealthyRecipeDetailsScreen(
                        modifier = Modifier.padding(innerPadding),
                        mockHealthyRecipes.first())
                }
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TabelaNutricionalTheme {
        Greeting("Android")
    }
}