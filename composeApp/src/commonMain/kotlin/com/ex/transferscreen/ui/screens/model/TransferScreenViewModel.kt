package com.ex.transferscreen.ui.screens.model

import androidx.lifecycle.ViewModel
import io.github.vinceglb.filekit.core.PlatformFiles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.round

class TransferScreenViewModel : ViewModel() {

    private val _stateFlow = MutableStateFlow(DashboardState())
    val state = _stateFlow.asStateFlow()

    init {
        calculateRecipientAmount()
    }

    fun onAmountChanges(string: String) {
        _stateFlow.value = _stateFlow.value.copy(sendAmount = string)
        calculateRecipientAmount()
    }

    fun onCurrencyChanges(item: CurrencyItem) {
        _stateFlow.value = _stateFlow.value.copy(sendCurrency = item)
        calculateRecipientAmount()
    }

    fun onCurrencyRecipientChanges(item: CurrencyItem) {
        _stateFlow.value = _stateFlow.value.copy(recipientCurrency = item)
        calculateRecipientAmount()
    }

    private fun calculateRecipientAmount() {
        val amount = _stateFlow.value.sendAmount.toDoubleOrNull() ?: 0.0
        val rates = _stateFlow.value.rates

        val rate = rates[_stateFlow.value.sendCurrency.title to _stateFlow.value.recipientCurrency.title] ?: 1.0
        val recipientAmount = amount * rate

        _stateFlow.value = _stateFlow.value.copy(
            recipientAmount = roundToTwoDecimals(recipientAmount),
            exchangeRate = generateExchangeRateString()
        )
    }

    private fun generateExchangeRateString(): String {
        val state = _stateFlow.value
        val rates = state.rates
        val sendCurrency = state.sendCurrency.title
        val recipientCurrency = state.recipientCurrency.title
        val rateToRecipient = rates[sendCurrency to recipientCurrency] ?: 1.0
        val rateToSend = rates[recipientCurrency to sendCurrency] ?: 1.0
        return "1 $sendCurrency = ${roundToTwoDecimals(rateToRecipient)} $recipientCurrency (1 $recipientCurrency = ${roundToTwoDecimals(rateToSend)} $sendCurrency)"
    }

    private fun roundToTwoDecimals(value: Double): String {
        val roundedValue = round(value * 100) / 100
        return if (roundedValue % 1.0 == 0.0) {
            roundedValue.toInt().toString()
        } else {
            roundedValue.toString()
        }
    }



    fun filesPicked(files: PlatformFiles?) {
        if (!files.isNullOrEmpty()) {
            _stateFlow.value = _stateFlow.value.copy(uploadedFile = files.size)
        }
    }

}