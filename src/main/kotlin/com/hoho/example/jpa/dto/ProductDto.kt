package com.hoho.example.jpa.dto

import com.hoho.example.jpa.config.NoArgsConstructor

@NoArgsConstructor
class ProductDto(
    var id: Long?,
    var name: String?,

    var memberProducts: MutableSet<MemberProductDto> = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProductDto) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Product(id=$id, name='$name')"
    }
}