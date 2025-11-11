package com.app.tabelanutricional.ui.screen.nav_host

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.tabelanutricional.core.navigation.UIRoute
import com.app.tabelanutricional.model.mock.mockHealthyRecipes
import com.app.tabelanutricional.ui.screen.healthy_recipe_details.HealthyRecipeDetailsScreen
import com.app.tabelanutricional.ui.screen.home.HomeScreen

@Composable
fun MainNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = UIRoute.Home) {
        composable<UIRoute.Home> {
            HomeScreen(
                onNavigateToDetails = { healthyRecipeId ->
                    navController.navigate(
                        UIRoute.HealthRecipeDetails(
                            healthyRecipeId = healthyRecipeId
                        )
                    )
                },
            )
        }

        composable<UIRoute.HealthRecipeDetails>{ navBackStackEntry ->
            val healthyRecipeId = navBackStackEntry.arguments?.getString("healthyRecipeId")

            healthyRecipeId?.let{
                val healthyRecipe =
                    mockHealthyRecipes.find { healthyRecipe -> healthyRecipeId == healthyRecipe.id.toString() }
                if(healthyRecipe == null) return@composable

                HealthyRecipeDetailsScreen(
                    healthyRecipe = healthyRecipe,
                    onClickFavorite = {},
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}