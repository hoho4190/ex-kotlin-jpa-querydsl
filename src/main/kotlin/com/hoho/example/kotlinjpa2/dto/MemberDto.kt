package com.hoho.example.kotlinjpa2.dto

import com.hoho.example.kotlinjpa2.config.NoArgsConstructor
import com.querydsl.core.annotations.QueryProjection

@NoArgsConstructor
class MemberDto @QueryProjection constructor(
    var id: Long?,
    var name: String?
) {
    var team: TeamDto? = null
    var locker: LockerDto? = null
    var memberProducts: MutableSet<MemberProductDto> = mutableSetOf()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MemberDto) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Member(id=$id, name='$name')"
    }
}