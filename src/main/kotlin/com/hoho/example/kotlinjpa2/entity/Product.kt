package com.hoho.example.kotlinjpa2.entity

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
@Table(name = "product")
class Product(
    @Id
    var id: Long,

    @Column
    var name: String,

    @OneToMany(mappedBy = "product")
    var memberProducts: MutableSet<MemberProduct> = mutableSetOf()
) : BaseEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Product) return false

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