package com.hoho.example.kotlinjpa2.entity

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
@Table(name = "locker")
class Locker(
    @Id
    var id: Long,

    @Column(length = 10)
    var name: String
) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Locker) return false

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