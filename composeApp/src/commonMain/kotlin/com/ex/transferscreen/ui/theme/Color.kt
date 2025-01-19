package com.ex.transferscreen.ui.theme

import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xFF06060A)
val OnPrimaryColor = Color(0xFF6C6B6B)
val SecondaryColor = Color(0xFF2E5BFF)
val OnSecondaryColor = Color(0xFF2431E0)
val SurfaceColor = Color(0xFF34AA54)
val OnSurfaceColor = Color(0xFFE3E6E9)
val BackgroundColor = Color(0xFFFFFFFF)
val OnBackgroundColor = Color(0xFFF2FCFD)
val TertiaryColor = Color(0xFF34AA54)

val gradientBlue = listOf(
    Color(0xFF2B48FF),
    Color(0xFF2336E5),
    Color(0xFF0B24BD),
)

fun ColorScheme.gradientList() = gradientBlue

@Composable
fun customColor() = colorScheme.onPrimary.copy(alpha = 0.1f)

@Composable
fun customCheckboxColors() =
    CheckboxColors(
        checkedCheckmarkColor = colorScheme.tertiary,
        uncheckedCheckmarkColor = Color.Transparent,
        checkedBoxColor = Color.Transparent,
        uncheckedBoxColor = Color.Transparent,
        disabledCheckedBoxColor =  colorScheme.onSurface,
        disabledUncheckedBoxColor = Color.Transparent,
        disabledIndeterminateBoxColor =  colorScheme.onSurface,
        checkedBorderColor = colorScheme.tertiary,
        uncheckedBorderColor = colorScheme.tertiary,
        disabledBorderColor =  colorScheme.onSurface,
        disabledUncheckedBorderColor =  colorScheme.onSurface,
        disabledIndeterminateBorderColor =  colorScheme.onSurface
    )


