package com.hoho.example.kotlinjpa2.dto

import com.hoho.example.kotlinjpa2.config.NoArgsConstructor
import com.querydsl.core.annotations.QueryProjection

@NoArgsConstructor
class LockerDto @QueryProjection constructor(
    var id: Long?,
    var name: String?
) {
    var member: MemberDto? = null

    @QueryProjection
    constructor (id: Long?, name: String?, member: MemberDto?) : this(id, name) {
        this.member = member
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LockerDto) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Locker(id=$id, name='$name')"
    }
}