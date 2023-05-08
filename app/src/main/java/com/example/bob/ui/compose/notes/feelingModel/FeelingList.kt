package com.example.bob.ui.compose.notes.feelingModel

object FeelingList {
    val feelingList: List<Feeling> = listOf(
        Feeling("\uD83E\uDD75", "Terrible"),
        Feeling("\uD83D\uDE1E", "Pas bien"),
        Feeling("\uD83D\uDE10", "Bof"),
        Feeling("\uD83D\uDE42", "Ca va"),
        Feeling("\uD83D\uDE0D", "Au top !"),
    )

    val feelingListString : List<String> = feelingList.map { it.icon + " - " + it.label }
}