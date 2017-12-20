package com.rosinante.nyobaanko.data

import java.util.*

/**
 * Created by Rosinante24 on 20/12/17.
 */
data class NoteDataClass(
        var id: Int = -1,
        var text: String? = null,
        var isPinned: Boolean = false,
        var createdAt: Date = Date(),
        var updatedAt: Date? = null
)