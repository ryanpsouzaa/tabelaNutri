package com.app.tabelanutricional.ui.component.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tabelanutricional.R

import com.app.tabelanutricional.ui.theme.Secondary
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme
import com.app.tabelanutricional.ui.theme.TabelaNutricionalTheme.sizing
import com.app.tabelanutricional.ui.theme.Typography

private const val NOTIFICATION_BADGE_OFFSET_X_RATIO = 0.7f
private const val NOTIFICATION_BADGE_OFFSET_Y_RATIO = 0.2f
private const val NOTIFICATION_BADGE_RADIUS_DIVIDER = 6
private const val NOTIFICATION_BADGE_RADIUS_DIVIDER_RED = 1.5f
@Composable
fun WelcomeHeader(
    modifier: Modifier = Modifier,
    userName: String,
    hasNewNotification: Boolean = true,
    onNotificationBellClick: () -> Unit = {}
) {
    var hasNewNotification by remember { mutableStateOf(hasNewNotification) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            modifier = Modifier
                .size(sizing.x2l),
            painter = painterResource(id = R.drawable.img_male_profile),
            contentDescription = stringResource(id = R.string.imagem_perfil_usuario)
        )

        Column(modifier = Modifier
            .weight(1f)
            .padding(sizing.sm)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.welcome_heading_ola_nome_user, userName),
                style = Typography.headlineMedium
            )
            Spacer(modifier = Modifier.padding(sizing.x2))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.welcome_heading_seja_bem_vindo),
                style = Typography.bodyLarge
            )
        }

        IconButton(
            onClick = {
                hasNewNotification = false
                onNotificationBellClick()
            }
        ){
            Icon(
                modifier = Modifier
                    .size(sizing.lg)
                    .drawWithContent {
                        drawContent()

                        if (hasNewNotification) {
                            val circleRadius = size.minDimension / NOTIFICATION_BADGE_RADIUS_DIVIDER

                            drawCircle(
                                color = Color.White,
                                radius = circleRadius,
                                center = Offset(
                                    x = size.width * NOTIFICATION_BADGE_OFFSET_X_RATIO,
                                    y = size.width * NOTIFICATION_BADGE_OFFSET_Y_RATIO
                                )
                            )

                            drawCircle(
                                color = Secondary,
                                radius = circleRadius / NOTIFICATION_BADGE_RADIUS_DIVIDER_RED,
                                center = Offset(
                                    x = size.width * NOTIFICATION_BADGE_OFFSET_X_RATIO,
                                    y = size.height * NOTIFICATION_BADGE_OFFSET_Y_RATIO
                                )
                            )
                        }
                    },
                painter = painterResource(id = R.drawable.ic_bell),
                contentDescription = stringResource(id = R.string.botao_notificacoes)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeHeaderWithNewNotificationsPreview() {
    TabelaNutricionalTheme{
        WelcomeHeader(
            modifier = Modifier.padding(sizing.md),
            userName = "Marcos",
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeHeaderWithoutNewNotificationsPreview() {
    TabelaNutricionalTheme{
        WelcomeHeader(
            hasNewNotification = false,
            userName = "Marcos",
            modifier = Modifier.padding(sizing.md))
    }
}