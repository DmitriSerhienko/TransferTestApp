package com.ex.transferscreen.ui.compose_elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ex.transferscreen.ui.screens.model.PurposeItem
import com.ex.transferscreen.ui.theme.CornerShape
import com.ex.transferscreen.ui.theme.Paddings
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import transferscreen.composeapp.generated.resources.Res
import transferscreen.composeapp.generated.resources.arrow_down
import transferscreen.composeapp.generated.resources.payment_purpose
import transferscreen.composeapp.generated.resources.purpose


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurposeEditField(
    purposeList: List<PurposeItem>,
    editable: Boolean
) {
    val (expanded, setExpanded) = remember { mutableStateOf(false) }
    var text by rememberSaveable { mutableStateOf("") }

    val label = if (editable) {
        stringResource(Res.string.payment_purpose)
    } else {
        stringResource(Res.string.purpose)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = setExpanded,
    ) {
        var onFocus by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .padding(vertical = Paddings.small)
                .fillMaxWidth()
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = !editable)
                .border(
                    width = Paddings.border,
                    color = if (onFocus) colorScheme.onSecondary else colorScheme.onSurface,
                    shape = RoundedCornerShape(CornerShape.shape)
                )
                .onFocusChanged { focusState -> onFocus = focusState.hasFocus }
        ) {
            if (text.isEmpty() && !onFocus) {
                Text(
                    text = label,
                    style = typography.titleLarge,
                    color = colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp)
                )
            }

            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = typography.titleLarge.copy(color = colorScheme.primary),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    keyboardType = KeyboardType.Text
                ),
                singleLine = true,
                readOnly = !editable,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 12.dp, bottom = 12.dp, end = if (!editable) 40.dp else 16.dp)
            )

            if (!editable) {
                Image(
                    painter = painterResource(Res.drawable.arrow_down),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .size(25.dp)
                        .rotate(if (expanded) 180f else 0f)
                )
            }
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { setExpanded(false) },
            modifier = Modifier.background(Color.White)
        ) {
            purposeList.forEachIndexed { index, item ->
                DropdownMenuItem(
                    modifier = Modifier.background(Color.White),
                    text = {
                        Text(
                            text = item.title,
                            style = typography.titleLarge,
                            color = colorScheme.primary
                        )
                    },
                    onClick = {
                        text = item.title
                        setExpanded(false)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
                if (index < purposeList.size - 1) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        HorizontalDivider(modifier = Modifier.fillMaxWidth(0.9f))
                    }
                }
            }
        }
    }
}
