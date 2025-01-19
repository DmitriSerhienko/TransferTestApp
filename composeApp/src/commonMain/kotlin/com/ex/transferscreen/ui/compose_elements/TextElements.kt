package com.ex.transferscreen.ui.compose_elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.ex.transferscreen.navigation.MainNavigation.FloatingButton
import org.jetbrains.compose.resources.stringResource
import presentation.theme.Fonts.poppinsFontFamily
import transferscreen.composeapp.generated.resources.Res
import transferscreen.composeapp.generated.resources.read_less
import transferscreen.composeapp.generated.resources.read_more

@Composable
fun TextHeader(txt: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = txt,
        color = colorScheme.primary,
        textAlign = TextAlign.Center,
        style = typography.bodyLarge
    )
}

@Composable
fun TextSubHeader(txt: String) {
    Text(
        text = txt,
        color = colorScheme.primary,
        textAlign = TextAlign.Start,
        style = typography.bodyMedium
    )
}

@Composable
fun TextPrimary(txt: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = txt,
        color = colorScheme.onPrimary,
        textAlign = TextAlign.Start,
        style = typography.bodyMedium
    )
}

@Composable
fun TextSecondary(txt: String, color: Color = colorScheme.onPrimary) {
    Text(
        text = txt,
        style = typography.bodySmall,
        textAlign = TextAlign.Start,
        color = color
    )
}

@Composable
fun ButtonText(txt: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = txt,
        color = colorScheme.background,
        textAlign = TextAlign.Center,
        style = typography.labelLarge
    )
}

@Composable
fun MaxLinesText(txt: String) {
    var expanded by remember { mutableStateOf(false) }
    val displayText = if (expanded) txt else txt.take(116)
    val longTxt = txt.length > 116
    val showReadMore = !expanded && longTxt

    Text(
        text = buildAnnotatedString {
            append(displayText)
            if (showReadMore) {
                append("...")
                withStyle(style = customSpan()) {
                    append(stringResource(Res.string.read_more))
                }
            } else {
                if (expanded){
                    append(" ")
                    withStyle(style = customSpan()) {
                        append(stringResource(Res.string.read_less))
                    }
                }
            }
        },
        style = typography.bodySmall,
        textAlign = TextAlign.Start,
        color = colorScheme.onPrimary,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = {
                if (longTxt) expanded = !expanded
            }
        )
    )
}

@Composable
fun customSpan() = SpanStyle(
    color = colorScheme.onSecondary,
    fontSize = 10.sp,
    fontWeight = FontWeight.Normal,
    fontFamily = poppinsFontFamily
)

@Composable
fun TextStyled(arg: String, boldArgs: String) {
    Text(
        text = buildAnnotatedString {
            append(arg)
            append(" ")
            withStyle(style = SpanStyle(
                fontSize = 10.sp,
                color = colorScheme.primary
            )
            ) {
                append(boldArgs)
            }
        },
        style = typography.bodySmall,
        color = colorScheme.onPrimary
    )
}