package com.hoho.example.jpa.dto

import com.hoho.example.jpa.config.NoArgsConstructor

@NoArgsConstructor
class TeamDto(
    var id: Long?,
    var name: String?,

    var members: MutableSet<MemberDto> = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TeamDto) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Team(id=$id, name='$name')"
    }
}