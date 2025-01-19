package com.ex.transferscreen.ui.compose_elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ex.transferscreen.navigation.LocalNavController
import com.ex.transferscreen.navigation.MainNavigation.Dashboard
import com.ex.transferscreen.navigation.MainNavigation.DebitCards
import com.ex.transferscreen.navigation.MainNavigation.Settings
import com.ex.transferscreen.navigation.MainNavigation.Statement
import com.ex.transferscreen.ui.theme.customColor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import transferscreen.composeapp.generated.resources.*

@Composable
fun NavigationBottomBar() {
    val mainNavController = LocalNavController.current
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    val customColor = customColor()

    val bottomShape = GenericShape { size, _ ->
        val width = size.width
        val height = size.height

        moveTo(0f, 0f)
        lineTo(width / 3, 0f)

        cubicTo(
            x1 = width / 2.2f,
            y1 = 0f,
            x2 = width / 2.45f,
            y2 = height / 3.5f,
            x3 = width / 2,
            y3 = height / 3f,
        )
        cubicTo(
            x1 = width - width / 2.45f,
            y1 = height / 3.5f,
            x2 = width - width / 2.2f,
            y2 = 0f,
            x3 = width - width / 3,
            y3 = 0f,
        )

        lineTo(width, 0f)
        close()
    }

        NavigationBar(
            modifier = Modifier.drawBehind {
                drawLine(
                    color = customColor,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 5f
                )
            }.padding(top = 0.dp),
            containerColor = colorScheme.background
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(customColor, shape = bottomShape)
            ) {
            items.forEach { bottomBarItem ->
                val selected = currentDestination == bottomBarItem.destination

                NavigationBarItem(
                    selected = selected,
                    icon = {
                        Icon(
                            painter = painterResource(bottomBarItem.icon),
                            contentDescription = null,
                            tint = if (!selected) colorScheme.onPrimary else colorScheme.secondary
                        )
                    },
                    label = {
                        Text(
                            text = stringResource(bottomBarItem.label),
                            color = if (!selected) colorScheme.onPrimary else colorScheme.secondary,
                            style = typography.titleSmall,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                    ),

                    onClick = {
                        mainNavController.navigate(bottomBarItem.destination)
                    }
                )
            }
        }
    }
}

data class BottomBarItem(
    val destination: String,
    val icon: DrawableResource,
    val label: StringResource,
)

val items = listOf(
    BottomBarItem(
        destination = Settings.route,
        icon = Res.drawable.icon_settings,
        label = Res.string.settings,
    ),
    BottomBarItem(
        destination = DebitCards.route,
        icon = Res.drawable.icon_cards ,
        label = Res.string.debit_cards,
    ),
    BottomBarItem(
        destination = Statement.route,
        icon = Res.drawable.icon_statement,
        label = Res.string.statement,
    ),
    BottomBarItem(
        destination = Dashboard.route,
        icon = Res.drawable.icon_dashboard ,
        label = Res.string.dashboard,
    )
)