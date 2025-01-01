package com.fadymarty.catopia.domain.use_case.app_entry

import com.loc.newsapp.domain.usecases.app_entry.SaveAppEntry

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry,
)
