package com.loc.newsapp.domain.usecases.app_entry

import com.fadymarty.catopia.domain.manager.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger,
) {

    suspend operator fun invoke() {
        localUserManger.saveAppEntry()
    }

}