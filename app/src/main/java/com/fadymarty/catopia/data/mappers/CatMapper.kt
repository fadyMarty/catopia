package com.fadymarty.catopia.data.mappers

import com.fadymarty.catopia.data.local.entity.CatEntity
import com.fadymarty.catopia.data.remote.dto.CatDto
import com.fadymarty.catopia.domain.model.Cat

fun CatDto.toCat(): Cat {
    return Cat(
        id = id,
        url = url,
        width = width,
        height = height
    )
}

fun CatEntity.toCat(): Cat {
    return Cat(
        id = id,
        url = url,
        width = width,
        height = height
    )
}

fun Cat.toCatEntity(): CatEntity {
    return CatEntity(
        id = id,
        url = url,
        width = width,
        height = height
    )
}