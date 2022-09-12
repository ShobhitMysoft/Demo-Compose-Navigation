package com.shobhitsagar.mycomposenavigation

const val NAME_KEY = "name"
const val MAIN_SCREEN_ROUTE = "main_screen"
const val DETAIL_SCREEN_ROUTE = "detail_screen"

sealed class Screens(val route: String) {
    object MainScreen: Screens(MAIN_SCREEN_ROUTE)
    object DetailScreen: Screens(DETAIL_SCREEN_ROUTE)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
