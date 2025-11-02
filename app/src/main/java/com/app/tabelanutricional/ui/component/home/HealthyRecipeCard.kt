package com.app.tabelanutricional.ui.component.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tabelanutricional.R
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme.sizing
import com.app.tabelanutricional.ui.theme.Typography

data class HealthyRecipe(
    val title: String,
    @DrawableRes val imageRes: Int,
    val calories: Float,
    val proteins: Float,
    val carbohydrates: Float
)

@Composable
fun HealthyRecipeCard(
    modifier: Modifier = Modifier,
    healthyRecipe: HealthyRecipe) {

    Row(
        modifier = modifier.fillMaxWidth(),

    ) {
        Image(
            modifier = Modifier
                .size(sizing.x2l)
                .clip(
                    shape = RoundedCornerShape(size = sizing.md)
                ),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = healthyRecipe.imageRes),
            contentDescription = stringResource(id = R.string.imagem_item_tabela_nutricional),
        )
        Spacer(modifier = Modifier.width(sizing.md))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(sizing.sm)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    modifier = Modifier.weight(1f),
                    text = healthyRecipe.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = Typography.headlineMedium,
                )
                Text(
                    modifier = Modifier,
                    text = "${healthyRecipe.calories} kcal",
                    style = Typography.bodyLarge

                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${healthyRecipe.proteins}g proteínas, ${healthyRecipe.carbohydrates}g carboidratos",
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = Typography.bodyLarge.copy(color = Color.Gray)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F2F2)
@Composable
private fun HealthyRecipeCardPreview() {
    TabelaNutricionalTheme {
        Column(
            modifier = Modifier.padding(sizing.md),
            verticalArrangement = Arrangement.spacedBy(sizing.md)
        ) {
            repeat(5){
                HealthyRecipeCard(
                    modifier = Modifier.height(100.dp).width(400.dp),
                    healthyRecipe = HealthyRecipe(
                        title = "Salada variada",
                        imageRes = R.drawable.img_assorted_salad,
                        calories = 221.15f,
                        proteins = 15.13f,
                        carbohydrates = 18.40f
                    )
                )
            }
        }
    }
}