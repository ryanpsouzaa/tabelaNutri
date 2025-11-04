package com.app.tabelanutricional.ui.component.healthy_recipe_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.app.tabelanutricional.R
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme.sizing
import com.app.tabelanutricional.ui.theme.Typography

@Composable
fun HealthyRecipeMainInfo(
    modifier: Modifier = Modifier,
    name: String,
    calories: Float,
    totalPortion: Int
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            style = Typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(sizing.lg))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column(
                verticalArrangement=  Arrangement.spacedBy(sizing.sm),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(R.string.energia),
                    style = Typography.headlineSmall
                )
                Text(
                    text = stringResource(R.string.valor_kcal, calories)
                )
            }
            Column(
                verticalArrangement=  Arrangement.spacedBy(sizing.sm),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = stringResource(R.string.porcao_total),
                    style = Typography.headlineSmall
                )
                Text(
                    text = stringResource(R.string.valor_g, totalPortion)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HealthyRecipeMainInfoPreview() {
    TabelaNutricionalTheme {
        HealthyRecipeMainInfo(
            modifier = Modifier.fillMaxHeight().padding(sizing.md),
            name = "Salada variada",
            calories = 221.15f,
            totalPortion = 240
        )
    }
}