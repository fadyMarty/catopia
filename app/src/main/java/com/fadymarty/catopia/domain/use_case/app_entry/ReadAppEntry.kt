package com.fadymarty.catopia.domain.use_case.app_entry

import com.fadymarty.catopia.domain.manager.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManger: LocalUserManger,
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }

}