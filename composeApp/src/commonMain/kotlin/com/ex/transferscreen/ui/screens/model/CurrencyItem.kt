package com.ex.transferscreen.ui.screens.model

import org.jetbrains.compose.resources.DrawableResource

data class CurrencyItem(
    val icon: DrawableResource,
    val title: String,
)

data class PurposeItem(
    val index: Int,
    val title: String,
)


