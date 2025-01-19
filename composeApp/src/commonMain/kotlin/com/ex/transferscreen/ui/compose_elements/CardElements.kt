package com.ex.transferscreen.ui.compose_elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.ex.transferscreen.ui.theme.Paddings

@Composable
fun CardStyled(
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(Paddings.large),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(width = Paddings.border, color = colorScheme.onSurface)
    ) {
        Column(
            modifier = Modifier.padding(Paddings.medium),
        ) {
            content()
        }
    }
}

@Composable
fun CardClickable(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    var selected by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier.fillMaxWidth().clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = { selected = !selected }
        ),
        colors = CardDefaults.cardColors(Color.White),
        border = BorderStroke(
            width = Paddings.border,
            color = if (selected) colorScheme.onSecondary else colorScheme.onSurface
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(Paddings.medium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}


