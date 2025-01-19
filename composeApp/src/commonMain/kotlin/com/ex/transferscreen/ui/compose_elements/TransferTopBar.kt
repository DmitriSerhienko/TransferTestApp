package com.ex.transferscreen.ui.compose_elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ex.transferscreen.ui.theme.Paddings
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import transferscreen.composeapp.generated.resources.Res
import transferscreen.composeapp.generated.resources.header
import transferscreen.composeapp.generated.resources.icon_back

@Composable
fun TransferTopBar() {
    Box(
        modifier = Modifier
            .padding(Paddings.large)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.height(16.dp),
                painter = painterResource(Res.drawable.icon_back),
                contentDescription = "",
                tint = colorScheme.primary,
            )
        }
        TextHeader(stringResource(Res.string.header))
    }
}