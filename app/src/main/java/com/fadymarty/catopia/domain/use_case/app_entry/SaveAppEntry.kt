package com.fadymarty.catopia.domain.use_case.app_entry

import com.fadymarty.catopia.domain.manager.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger,
) {

    suspend operator fun invoke() {
        localUserManger.saveAppEntry()
    }
}