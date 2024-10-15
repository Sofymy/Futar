package com.zenitech.futar.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(){
    @Serializable
    data object Boarding: Screen()

    @Serializable
    data object Login: Screen()

    @Serializable
    data object TrafficNumber: Screen()

    @Serializable
    data object AutomaticTrafficNumber: Screen()

    @Serializable
    data object Home: Screen()

    @Serializable
    data object Welcome: Screen()

}