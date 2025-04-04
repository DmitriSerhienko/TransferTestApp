package com.ex.transferscreen.ui.compose_elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


@Composable
fun SpaceVertical(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable
fun SpaceHorizontal(width: Dp) {
    Spacer(modifier = Modifier.width(width))
}