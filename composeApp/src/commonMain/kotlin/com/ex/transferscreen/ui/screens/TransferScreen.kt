package com.ex.transferscreen.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ex.transferscreen.ui.compose_elements.ButtonText
import com.ex.transferscreen.ui.compose_elements.CardClickable
import com.ex.transferscreen.ui.compose_elements.CardStyled
import com.ex.transferscreen.ui.compose_elements.CheckBoxRow
import com.ex.transferscreen.ui.compose_elements.DropDownField
import com.ex.transferscreen.ui.compose_elements.PurposeEditField
import com.ex.transferscreen.ui.compose_elements.SpaceVertical
import com.ex.transferscreen.ui.compose_elements.TextPrimary
import com.ex.transferscreen.ui.compose_elements.TextSecondary
import com.ex.transferscreen.ui.compose_elements.TextStyled
import com.ex.transferscreen.ui.compose_elements.TextSubHeader
import com.ex.transferscreen.ui.compose_elements.UploadDocumentCard
import com.ex.transferscreen.ui.screens.model.TransferScreenViewModel
import com.ex.transferscreen.ui.theme.CornerShape
import com.ex.transferscreen.ui.theme.Paddings
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import transferscreen.composeapp.generated.resources.*

@Composable
@Preview
fun TransferScreen(
    viewModel: TransferScreenViewModel = koinViewModel<TransferScreenViewModel>(),
    innerPaddings: PaddingValues
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxHeight().navigationBarsPadding().padding(
            top = innerPaddings.calculateTopPadding(),
            bottom = innerPaddings.calculateBottomPadding()
        ).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextSubHeader(stringResource(Res.string.header_question))
        CardStyled {
            TextPrimary(stringResource(Res.string.send_funds_label))
            DropDownField(
                curItemsList = state.currencyList,
                maxInputSize = state.maxInputSize,
                inputEnabled = true,
                inputValue = state.sendAmount,
                inputItem = state.sendCurrency,
                viewModel::onAmountChanges,
                viewModel::onCurrencyChanges
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextStyled(stringResource(Res.string.balance),  stringResource(Res.string.balance_amount))
                TextStyled(stringResource(Res.string.available_balance),  stringResource(Res.string.available_balance_amount))
            }
            SpaceVertical(Paddings.small)
            TextPrimary(stringResource(Res.string.recipient_label))
            DropDownField(
                curItemsList = state.currencyList,
                maxInputSize = state.maxInputSize,
                inputEnabled = false,
                inputValue = state.recipientAmount,
                inputItem = state.recipientCurrency,
                viewModel::onAmountChanges,
                viewModel::onCurrencyRecipientChanges
            )
            SpaceVertical(Paddings.small)
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextSecondary(stringResource(Res.string.exchange_header))
                TextSecondary(state.exchangeRate, colorScheme.primary)
            }
            SpaceVertical(Paddings.large)
            TextSecondary(stringResource(Res.string.exchange_explanation))
            SpaceVertical(Paddings.large)
            TextSubHeader(stringResource(Res.string.transfer_options))
            SpaceVertical(Paddings.small)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Paddings.extra)
            ) {
                CardClickable(
                    modifier = Modifier.weight(1f),
                    content = { TextSubHeader(stringResource(Res.string.regular)) }
                )
                CardClickable(
                    modifier = Modifier.weight(1f),
                    content = { TextSubHeader(stringResource(Res.string.express)) }
                )
            }
            SpaceVertical(Paddings.extra)
            PurposeEditField(
                purposeList = state.purposeList,
                editable = false,
            )
            PurposeEditField(
                purposeList = state.purposeList,
                editable = true,
            )
            SpaceVertical(Paddings.extra)
            TextPrimary(stringResource(Res.string.supported_documents))
            SpaceVertical(Paddings.medium)
            UploadDocumentCard(viewModel)
            SpaceVertical(Paddings.medium)
            TextSecondary(stringResource(Res.string.limitations))
        }
        SpaceVertical(Paddings.extra)
        CheckBoxRow(stringResource(Res.string.beneficiary_label))
        HorizontalDivider(modifier = Modifier.fillMaxWidth().padding(horizontal = Paddings.large))
        SpaceVertical(Paddings.small)
        CheckBoxRow(stringResource(Res.string.agree_statement))
        SpaceVertical(Paddings.small)
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().padding(horizontal = Paddings.large),
            shape = RoundedCornerShape(CornerShape.shape),
            colors = ButtonDefaults.buttonColors(containerColor = colorScheme.tertiary)
        ){
            ButtonText(stringResource(Res.string.preview_transfer).uppercase())
        }
        SpaceVertical(Paddings.extraLarge)

    }

}