package com.sunhurov.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class To(val directions: NavDirections): NavigationCommand()
    object Back: NavigationCommand()
}