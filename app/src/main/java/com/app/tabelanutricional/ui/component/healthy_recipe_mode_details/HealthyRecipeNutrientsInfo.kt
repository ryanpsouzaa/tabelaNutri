package com.app.tabelanutricional.ui.component.healthy_recipe_mode_details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tabelanutricional.R
import com.app.tabelanutricional.model.NutrientUnit
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme.sizing
import com.app.tabelanutricional.ui.theme.Typography

@Composable
fun HealthyRecipeNutrientsInfo(
    modifier: Modifier = Modifier,
    name: String,
    value: Float,
    isSubNutrient: Boolean = false,
    unit: NutrientUnit
    ) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            modifier = modifier
                .weight(1f)
                .padding(start = if(isSubNutrient) sizing.md else sizing.x0),
            text = name,
            style = if(isSubNutrient) Typography.bodyMedium else Typography.headlineSmall,
            )
        Text(
            text = stringResource(R.string.valor_com_unidade_recipe_details, value, unit.symbol),
            style = Typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun HealthyRecipeNutrientsInfoPreview() {
    TabelaNutricionalTheme {
        Column {
            HealthyRecipeNutrientsInfo(
                modifier = Modifier.padding(16.dp),
                name = "Energia",
                value = 221.15f,
                unit = NutrientUnit.KCAL
            )

            HealthyRecipeNutrientsInfo(
                modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 16.dp),
                name = "Fibra Alimentar",
                value = 5.83f,
                unit = NutrientUnit.GRAM,
                isSubNutrient = true
            )
        }
    }
}