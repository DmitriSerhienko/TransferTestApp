package com.ex.transferscreen.navigation

sealed class MainNavigation(val route: String) {

    data object Settings : MainNavigation(route = "settings")
    data object DebitCards : MainNavigation(route = "debit_cards")
    data object Statement : MainNavigation(route = "statement")
    data object Dashboard : MainNavigation(route = "dashboard")
    data object FloatingButton : MainNavigation(route = "fab")

}