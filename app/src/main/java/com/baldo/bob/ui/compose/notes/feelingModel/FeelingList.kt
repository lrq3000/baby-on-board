package com.baldo.bob.ui.compose.notes.feelingModel

import com.baldo.bob.R

object FeelingList {
    val feelingList: List<Feeling> = listOf(
        Feeling("\uD83E\uDD75", R.string.terrible),
        Feeling("\uD83D\uDE1E", R.string.not_good),
        Feeling("\uD83D\uDE10", R.string.neutral),
        Feeling("\uD83D\uDE42", R.string.good),
        Feeling("\uD83D\uDE0D", R.string.great),
    )

//    val feelingListString : List<String> = feelingList.map { it.icon + " - " + it.label }
}
