package com.example.abay

import com.example.abay.database.QaraSoz
import com.example.abay.network.QaraSozEntity

fun mapQaraSozEntityToQaraSoz(entity: QaraSozEntity): QaraSoz {
    return QaraSoz(
        title = entity.title,
        text = entity.text,
        number = entity.number
    )
}