package com.hoho.example.jpa.entity

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
@Table(name = "member")
class Member(
    @Id
    var id: Long,

    @Column
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    var team: Team,

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "locker_id", unique = true, nullable = false)
    var locker: Locker,

    @OneToMany(mappedBy = "member")
    var memberProducts: MutableSet<MemberProduct> = mutableSetOf()
) : BaseEntity() {
    fun changeTeam(team: Team) {
        this.team.members.remove(this)

        this.team = team
        team.members.add(this)
    }

    fun changeTeam() {
        team.members.add(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Member) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Member(id=$id, name='$name', ${super.toString()})"
    }
}