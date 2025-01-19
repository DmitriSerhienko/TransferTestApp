package com.ex.transferscreen.ui.theme

import androidx.compose.ui.unit.dp

object Paddings {
    val border = 1.dp
    val small = 5.dp
    val medium = 10.dp
    val large = 15.dp
    val extra = 20.dp
    val extraLarge = 25.dp
}

object CornerShape {
    val shape = 8.dp
}

fun getValidatedNumber(text: String, beforeDot: Int, afterDot: Int): String {
    val filteredChars = text.filterIndexed { index, c ->
        c in "0123456789" || (c == '.' && text.indexOf('.') == index)
    }
    return if(filteredChars.contains('.')) {
        val beforeDecimal = filteredChars.substringBefore('.')
        val afterDecimal = filteredChars.substringAfter('.')
        beforeDecimal.take(beforeDot) + "." + afterDecimal.take(afterDot)
    } else {
        filteredChars.take(beforeDot)
    }
}
