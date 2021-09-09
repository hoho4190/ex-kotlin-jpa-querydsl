package com.hoho.example.kotlinjpa2.dto

import com.hoho.example.kotlinjpa2.config.NoArgsConstructor

@NoArgsConstructor
class MemberProductDto (
    var id: Long?,
    var etc: String?,

    var member: MemberDto?,
    var product: MemberProductDto?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MemberProductDto) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "MemberProduct(id=$id, etc='$etc')"
    }
}