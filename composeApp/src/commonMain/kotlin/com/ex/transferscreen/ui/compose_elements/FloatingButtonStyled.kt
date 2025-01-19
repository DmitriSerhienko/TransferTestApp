package com.ex.transferscreen.ui.compose_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.ex.transferscreen.navigation.LocalNavController
import com.ex.transferscreen.navigation.MainNavigation.FloatingButton
import com.ex.transferscreen.ui.theme.gradientList
import org.jetbrains.compose.resources.painterResource
import transferscreen.composeapp.generated.resources.Res
import transferscreen.composeapp.generated.resources.icon_plus

@Composable
fun FloatingButtonStyled() {
    val mainNavController = LocalNavController.current

    val size = 50.dp
    val sizePx = with(LocalDensity.current) { size.toPx() }
    val gradientOffsetX = sizePx / 2f
    val gradientOffsetY = sizePx / 3f
    val gradientRadius = sizePx / 2f + gradientOffsetX / 3f
    Box(
        modifier = Modifier.offset(y = 40.dp)
            .size(size)
            .background(
                shape = CircleShape,
                brush = Brush.radialGradient(
                    colors = colorScheme.gradientList(),
                    center = Offset(gradientOffsetX, gradientOffsetY),
                    radius = gradientRadius,
                ),
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    mainNavController.navigate(FloatingButton.route)
                },
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(Res.drawable.icon_plus),
            contentDescription = null,
            tint = colorScheme.background,
        )
    }
}