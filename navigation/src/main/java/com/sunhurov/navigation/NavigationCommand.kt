package com.sunhurov.navigation

import androidx.navigation.NavDirections

/**
 * Created by Yurii Sunhurov on 13.05.2020
 */

sealed class NavigationCommand {
    data class To(val directions: NavDirections): NavigationCommand()
    object Back: NavigationCommand()
}