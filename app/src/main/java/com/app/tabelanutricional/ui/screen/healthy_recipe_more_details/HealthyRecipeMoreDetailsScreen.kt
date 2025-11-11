package com.app.tabelanutricional.ui.screen.healthy_recipe_more_details

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tabelanutricional.R
import com.app.tabelanutricional.model.HealthyRecipe
import com.app.tabelanutricional.model.mock.mockHealthyRecipes
import com.app.tabelanutricional.ui.component.healthy_recipe_mode_details.HealthyRecipeNutrientsInfo
import com.app.tabelanutricional.ui.screen.healthy_recipe_details.HealthyRecipeDetailsScreen
import com.app.tabelanutricional.ui.theme.SurfaceElement
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme.sizing
import com.app.tabelanutricional.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthyRecipeMoreDetailsScreen(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    healthyRecipe: HealthyRecipe,
    onDismiss: () -> Unit
) {
    val isPreview = LocalInspectionMode.current

    LaunchedEffect(Unit) {
        if(isPreview) sheetState.expand()
    }

    ModalBottomSheet(
        modifier = modifier,
        contentColor = MaterialTheme.colorScheme.onBackground,
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = {
            Box(
                modifier = Modifier.padding(sizing.md)
            ){
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth(0.2f)
                        .height(sizing.sm)
                ) {
                    drawRoundRect(
                        color = SurfaceElement,
                        cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx())
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = sizing.md)
                .padding(bottom = sizing.lg),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(sizing.md)
        ) {
            Text(
                text = stringResource(id = R.string.mais_detalhes),
                style = Typography.headlineMedium
            )

            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.calories
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.proteins
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.carbohydrates
            )
            HealthyRecipeNutrientsInfo(
                isSubNutrient = true,
                nutrient = healthyRecipe.fiber
            )
            HealthyRecipeNutrientsInfo(
                isSubNutrient = true,
                nutrient = healthyRecipe.sugar
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.totalFat
            )
            HealthyRecipeNutrientsInfo(
                isSubNutrient = true,
                nutrient = healthyRecipe.saturatedFat
            )
            HealthyRecipeNutrientsInfo(
                isSubNutrient = true,
                nutrient = healthyRecipe.transFat
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.cholesterol
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.sodium
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.potassium
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.calcium
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.iron
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.magnesium
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.vitaminC
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.vitaminD
            )
            HealthyRecipeNutrientsInfo(
                nutrient = healthyRecipe.vitaminB6
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun HealthyRecipeMoreDetailsScreenPreview() {
    TabelaNutricionalTheme{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SurfaceElement)
        ) {
            HealthyRecipeMoreDetailsScreen(
                sheetState = rememberModalBottomSheetState(),
                onDismiss = {},
                healthyRecipe = mockHealthyRecipes.first()
            )

        }
    }


}