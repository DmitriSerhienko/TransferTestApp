package presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import presentation.theme.Fonts.poppinsFontFamily
import transferscreen.composeapp.generated.resources.Res
import transferscreen.composeapp.generated.resources.*

object Fonts {
    val poppinsFontFamily
        @Composable get() = FontFamily(
            Font(
                Res.font.poppins_regular,
                FontWeight.Normal,
                FontStyle.Normal
            ),
            Font(
                Res.font.poppins_medium,
                FontWeight.Medium,
                FontStyle.Normal
            ),
            Font(
                Res.font.poppins_semibold,
                FontWeight.SemiBold,
                FontStyle.Normal
            ),
            Font(
                Res.font.poppins_bold,
                FontWeight.Bold,
                FontStyle.Normal
            )
        )
}

val Typography
    @Composable
    get() = Typography(
        titleLarge = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily
        ),
        titleMedium = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily
        ),
        titleSmall = TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = poppinsFontFamily
        ),
        bodyLarge = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFontFamily
        ),
        bodyMedium = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = poppinsFontFamily
        ),
        bodySmall = TextStyle(
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = poppinsFontFamily
        ),
        labelLarge = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = poppinsFontFamily
        )
    )