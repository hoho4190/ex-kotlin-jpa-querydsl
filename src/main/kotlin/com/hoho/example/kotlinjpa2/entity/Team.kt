package com.hoho.example.kotlinjpa2.entity

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
@Table(name = "team")
class Team(
    @Id
    var id: Long,

    @Column
    var name: String,

    @OneToMany(mappedBy = "team")
    var members: MutableSet<Member> = mutableSetOf()
) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Team) return false

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