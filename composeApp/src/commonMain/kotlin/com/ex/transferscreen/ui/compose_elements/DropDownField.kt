package com.ex.transferscreen.ui.compose_elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ex.transferscreen.ui.screens.model.CurrencyItem
import com.ex.transferscreen.ui.theme.CornerShape
import com.ex.transferscreen.ui.theme.Paddings
import com.ex.transferscreen.ui.theme.getValidatedNumber
import org.jetbrains.compose.resources.painterResource
import transferscreen.composeapp.generated.resources.Res
import transferscreen.composeapp.generated.resources.arrow_down

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownField(
    curItemsList: List<CurrencyItem>,
    maxInputSize: Int,
    inputEnabled: Boolean,
    inputValue: String,
    inputItem: CurrencyItem,
    onAmountChanges: (String) -> Unit,
    onCurrencyChanges: (CurrencyItem) -> Unit
) {
    val (expanded, setExpanded) = remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = setExpanded,
    ) {
        BasicTextField(
            modifier = Modifier
                .padding(vertical = Paddings.small)
                .fillMaxWidth()
                .height(60.dp)
                .border(
                    width = Paddings.border,
                    color = colorScheme.onSecondary,
                    shape = RoundedCornerShape(CornerShape.shape)
                ),
            value = TextFieldValue(text = inputValue, selection = TextRange(inputValue.length)),
            onValueChange = {
                if (it.text.length <= maxInputSize) {
                    onAmountChanges(getValidatedNumber(it.text, 8, 2))
                }
            },
            enabled = inputEnabled,
            textStyle = typography.titleLarge,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Number
            ),
        ) { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = inputValue,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                interactionSource = interactionSource,
                visualTransformation = VisualTransformation.None,
                suffix = {
                    Row(
                        modifier = Modifier
                            .menuAnchor(MenuAnchorType.SecondaryEditable)
                            .width(100.dp)
                        ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        VerticalDivider(modifier = Modifier.fillMaxHeight(0.8f))
                        Image(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(inputItem.icon),
                            contentDescription = inputItem.title,
                        )
                        Text(
                            text = inputItem.title,
                            color = colorScheme.primary,
                            textAlign = TextAlign.Center,
                            style = typography.titleMedium
                        )
                        Icon(
                            modifier = Modifier.rotate(if (expanded) 180f else 0f).size(25.dp),
                            painter = painterResource(Res.drawable.arrow_down),
                            tint = null,
                            contentDescription = null
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { setExpanded(false) },
            modifier = Modifier.background(Color.White)
        ) {
            curItemsList.forEachIndexed { index, item ->
                DropdownMenuItem(
                    modifier = Modifier.background(Color.White),
                    leadingIcon = {
                        Image(
                            modifier = Modifier.size(40.dp),
                            painter = painterResource(item.icon),
                            contentDescription = item.title,
                        )
                    },
                    text = {
                        Text(
                            item.title,
                            style = typography.titleLarge,
                            color = colorScheme.primary
                        )
                    },
                    onClick = {
                        onCurrencyChanges(item)
                        setExpanded(false)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
                if (index < curItemsList.size - 1) {
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