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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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
import com.app.tabelanutricional.ui.theme.Primary
import com.app.tabelanutricional.ui.theme.SurfaceElement
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme.sizing

private const val RECIPE_DETAILS_IMAGE_SCALE = 1.2f
private const val RECIPE_DETAILS_IMAGE_ROTATION_ANIMATION = 360f
private const val RECIPE_DETAILS_IMAGE_ANIMATION_DURATION_MILLI = 1000

@Composable
fun HealthyRecipeDetailsScreen(
    modifier: Modifier = Modifier,
    healthyRecipe: HealthyRecipe) {
    var isImageVisible: Boolean by remember { mutableStateOf(false) }
    val scale = remember { Animatable(initialValue = 0f) }
    val rotation = remember { Animatable(initialValue = 0f) }

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
                    onClick = {}
                )

                LoveButton(
                    modifier = Modifier
                        .shadow(
                            elevation = sizing.lg,
                            shape = CircleShape,
                            spotColor = Primary
                        ),
                    onClick = {}
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
                name = healthyRecipe.title,
                calories = healthyRecipe.calories,
                totalPortion = healthyRecipe.totalPortion
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
                text = stringResource(R.string.mais_detalhes)
                ){ }
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
    }
}

@Composable
fun HealthyRecipeBars(
    modifier: Modifier = Modifier,
    healthyRecipe: HealthyRecipe
) {
    HealthyRecipeNutrientBar(
        modifier = Modifier,
        stringResource(R.string.proteinas),
        healthyRecipe.proteins,
        maxValue = 35f
    )
    HealthyRecipeNutrientBar(
        modifier = Modifier,
        stringResource(R.string.carboidratos),
        healthyRecipe.carbohydrates,
        maxValue = 35f
    )
    HealthyRecipeNutrientBar(
        modifier = Modifier,
        stringResource(R.string.acucar),
        healthyRecipe.sugar,
        maxValue = 35f
    )
    HealthyRecipeNutrientBar(
        modifier = Modifier,
        stringResource(R.string.gorduras),
        healthyRecipe.fat,
        maxValue = 35f
    )

}

@Preview(showBackground = true)
@Composable
private fun HealthyRecipeDetailsScreenPreview() {
    HealthyRecipeDetailsScreen(
        modifier = Modifier,
        healthyRecipe = mockHealthyRecipes.first()
    )
}