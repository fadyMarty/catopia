package com.fadymarty.catopia.presentation.onboarding

import androidx.annotation.DrawableRes
import com.fadymarty.catopia.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)


val pages = listOf(
    Page(
        title = "Welcome to Catopia!",
        description = "Dive into a world filled with the cutest cat photos. Your daily dose of feline joy starts here!",
        image = R.drawable.onboarding_1
    ),
    Page(
        title = "Explore Adorable Cats",
        description = "Swipe through a gallery of charming cats, from playful kittens to majestic felines.\n",
        image = R.drawable.onboarding_2
    ),
    Page(
        title = "Bookmark What You Love",
        description = "Found a cat you adore? Tap the heart icon to save it to your favorites and enjoy it anytime.",
        image = R.drawable.onboarding_3
    )
)