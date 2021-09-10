package com.hoho.example.jpa.dto

import java.util.*

abstract class BaseDto(
    var createAt: Date? = null,
    var updateAt: Date? = null
) {
    override fun toString(): String {
        return "createAt=$createAt, updateAt=$updateAt"
    }
}