package com.ex.transferscreen.ui.screens.model

import transferscreen.composeapp.generated.resources.Res
import transferscreen.composeapp.generated.resources.*

data class DashboardState(
    val currencyList: List<CurrencyItem> = listOf(
        CurrencyItem(Res.drawable.cur_eur, "EUR"),
        CurrencyItem(Res.drawable.usd, "USD"),
        CurrencyItem(Res.drawable.cur_ils, "ILS")
    ),
    val maxInputSize: Int = 10,
    val purposeList: List<PurposeItem> = listOf(
        PurposeItem(1, "Education"),
        PurposeItem(2, "Present"),
        PurposeItem(3, "Other")
    ),
    val uploadedFile: Int = 0,
    val sendAmount: String = "100",
    val recipientAmount: String = "0",
    val sendCurrency: CurrencyItem = currencyList[0],
    val recipientCurrency: CurrencyItem = currencyList[2],
    val rates: Map<Pair<String, String>, Double> = mapOf(
        ("EUR" to "USD") to 1.03,
        ("EUR" to "ILS") to 3.62,
        ("USD" to "EUR") to 0.97,
        ("USD" to "ILS") to 3.59,
        ("ILS" to "EUR") to 0.27,
        ("ILS" to "USD") to 0.28
    ),
    val exchangeRate: String = ""
)

