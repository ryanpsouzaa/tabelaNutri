package com.app.tabelanutricional.ui.component.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.tabelanutricional.R
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme.sizing
import com.app.tabelanutricional.ui.theme.Typography
import java.util.UUID





@Composable
fun WellnessNewsCard(
    modifier: Modifier = Modifier,
    wellnessNews: WellnessNews
){
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            //recorta a imagem no sentido que nao fique estrapolando imagem para fora do tamanho
            contentScale = ContentScale.Crop,
            painter = painterResource(id = wellnessNews.imageRes),
            contentDescription = stringResource(id = R.string.imagem_item_tabela_nutricional)
        )
        LazyRow(
            modifier = Modifier.padding(top = sizing.sm),
            horizontalArrangement = Arrangement.spacedBy(sizing.xs)
        ){
            items(count = wellnessNews.tags.size){index ->
                Text(
                    modifier = Modifier
                        .border(
                            width = sizing.x1,
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = RoundedCornerShape(size = sizing.xs)
                        )
                        .padding(sizing.sm),
                    style = Typography.titleSmall.copy(fontSize = 12.sp),
                    text = wellnessNews.tags[index].description,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = sizing.md),
            text = wellnessNews.title,
            style = Typography.titleSmall,
            minLines = 1,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.tempo_leitura_da_noticia, wellnessNews.readTimeInMinutes),
            style = Typography.titleSmall.copy(color = Color(0xFF8A8A8A))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WellnessNewsCardPreview() {
    TabelaNutricionalTheme{
        LazyRow(
            modifier = Modifier.padding(sizing.md),
            horizontalArrangement = Arrangement.spacedBy(sizing.sm)
        ) {
            items(count = 3){
                WellnessNewsCard(
                    modifier = Modifier.width(200.dp),
                    wellnessNews = WellnessNews(
                        title = "A importância da tabela nutricional na alimentação consciente",
                        readTimeInMinutes = 5,
                        imageRes = R.drawable.img_nutritional_news_1,
                        tags = listOf(
                            WellnessNewsTag.WELLNESS,
                            WellnessNewsTag.NUTRITION
                        )
                    )
                )
            }
        }
    }
}