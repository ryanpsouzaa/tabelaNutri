package com.app.tabelanutricional.model

import androidx.annotation.DrawableRes
import java.util.UUID

data class HealthyRecipe(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    @DrawableRes val imageRes: Int,
    val calories: Float,
    val proteins: Float,
    val carbohydrates: Float,
    val totalPortion: Int,
    val sugar: Float,
    val fat: Float
)