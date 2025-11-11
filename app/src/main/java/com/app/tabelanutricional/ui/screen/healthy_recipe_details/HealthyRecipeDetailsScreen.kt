package com.app.tabelanutricional.ui.screen.healthy_recipe_details

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.app.tabelanutricional.R
import com.app.tabelanutricional.model.HealthyRecipe
import com.app.tabelanutricional.model.mock.mockHealthyRecipes
import com.app.tabelanutricional.ui.component.BackButton
import com.app.tabelanutricional.ui.component.LoveButton
import com.app.tabelanutricional.ui.component.PrimaryButton
import com.app.tabelanutricional.ui.component.healthy_recipe_details.HealthyRecipeMainInfo
import com.app.tabelanutricional.ui.component.healthy_recipe_details.HealthyRecipeNutrientBar
import com.app.tabelanutricional.ui.screen.healthy_recipe_more_details.HealthyRecipeMoreDetailsScreen
import com.app.tabelanutricional.ui.theme.Primary
import com.app.tabelanutricional.ui.theme.SurfaceElement
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme.sizing
import kotlinx.coroutines.launch

private const val RECIPE_DETAILS_IMAGE_SCALE = 1.2f
private const val RECIPE_DETAILS_IMAGE_ROTATION_ANIMATION = 360f
private const val RECIPE_DETAILS_IMAGE_ANIMATION_DURATION_MILLI = 1000

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthyRecipeDetailsScreen(
    modifier: Modifier = Modifier,
    healthyRecipe: HealthyRecipe,
    onNavigateBack: () -> Unit,
    onClickFavorite: (isSelected: Boolean) -> Unit) {

    var isImageVisible: Boolean by remember { mutableStateOf(false) }
    val scale = remember { Animatable(initialValue = 0f) }
    val rotation = remember { Animatable(initialValue = 0f) }

    var showMoreDetails by remember{ mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val moreDetailsSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(key1 = Unit) {
        isImageVisible = true

        scale.animateTo(
            targetValue = RECIPE_DETAILS_IMAGE_SCALE,
            animationSpec = tween(
                durationMillis = RECIPE_DETAILS_IMAGE_ANIMATION_DURATION_MILLI,
                easing = LinearEasing
            )
        )
    }

    LaunchedEffect(key1 = Unit) {
        rotation.animateTo(
            targetValue = RECIPE_DETAILS_IMAGE_ROTATION_ANIMATION,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = SurfaceElement)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(sizing.lg),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween ) {
                BackButton(
                    modifier = Modifier
                        .shadow(
                            elevation = sizing.lg,
                            shape = CircleShape,
                            spotColor = Primary
                        ),
                    onClick = onNavigateBack
                )

                LoveButton(
                    modifier = Modifier
                        .shadow(
                            elevation = sizing.lg,
                            shape = CircleShape,
                            spotColor = Primary
                        ),
                    onClick = { isSelected ->
                        onClickFavorite(isSelected)
                    }
                )
            }

            if(isImageVisible){
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer{
                            scaleX = scale.value
                            scaleY = scale.value
                            rotationZ = rotation.value
                        }
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.img_dish_with_shadow),
                    contentScale = ContentScale.FillWidth,
                    contentDescription = stringResource(R.string.imagem_item_tabela_nutricional)
                )
            }

            HealthyRecipeMainInfo(
                modifier = Modifier,
                name = healthyRecipe.name,
                calories = healthyRecipe.calories.value,
                totalPortion = healthyRecipe.totalPortionInGrams
                )

            HealthyRecipeBars(
                modifier = Modifier.padding(horizontal = sizing.md),
                healthyRecipe)

            Spacer(modifier = Modifier.height(sizing.sm))

            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(sizing.x3l)
                    .padding(horizontal = sizing.md),
                text = stringResource(R.string.mais_detalhes),
                onClick = {
                    showMoreDetails = true
                }
            )
        }
        Box(
            modifier = Modifier
                .zIndex(-1f)
                .align(Alignment.BottomStart)
                .clip(
                    RoundedCornerShape(
                        topStart = sizing.xl,
                        topEnd = sizing.xl
                    )
                )
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        )

        if(showMoreDetails){
            LaunchedEffect(moreDetailsSheetState) {
                moreDetailsSheetState.show()
            }
            HealthyRecipeMoreDetailsScreen(
                sheetState = moreDetailsSheetState,
                healthyRecipe = healthyRecipe,
                onDismiss = {
                    coroutineScope.launch {
                        moreDetailsSheetState.hide()
                    }.invokeOnCompletion {
                        if (!moreDetailsSheetState.isVisible) {
                            showMoreDetails = false
                        }
                    }
                }
            )
        }

    }
}

@Composable
fun HealthyRecipeBars(
    modifier: Modifier = Modifier,
    healthyRecipe: HealthyRecipe
) {
    HealthyRecipeNutrientBar(
        modifier = Modifier,
        stringResource(healthyRecipe.proteins.nameRes),
        healthyRecipe.proteins.value,
        maxValue = 35f
    )
    HealthyRecipeNutrientBar(
        modifier = Modifier,
        stringResource(healthyRecipe.carbohydrates.nameRes),
        healthyRecipe.carbohydrates.value,
        maxValue = 35f
    )
    HealthyRecipeNutrientBar(
        modifier = Modifier,
        stringResource(healthyRecipe.sugar.nameRes),
        healthyRecipe.sugar.value,
        maxValue = 35f
    )
    HealthyRecipeNutrientBar(
        modifier = Modifier,
        stringResource(healthyRecipe.fat.nameRes),
        healthyRecipe.fat.value,
        maxValue = 35f
    )
}

@Preview(showBackground = true)
@Composable
private fun HealthyRecipeDetailsScreenPreview() {
    HealthyRecipeDetailsScreen(
        modifier = Modifier,
        healthyRecipe = mockHealthyRecipes.first(),
        onNavigateBack = {},
        onClickFavorite = {}
    )
}