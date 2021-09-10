package com.hoho.example.kotlinjpa2.entity

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
@Table(name = "member_product")
class MemberProduct(
    @Id
    @GeneratedValue
    var id: Long?,

    @Column
    var etc: String?,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    var member: Member,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product
) : BaseEntity() {
    fun changeMember(member: Member) {
        this.member.memberProducts.remove(this)

        this.member = member
        member.memberProducts.add(this)
    }

    fun changeProduct(product: Product) {
        this.product.memberProducts.remove(this)

        this.product = product
        product.memberProducts.add(this)
    }

    fun changeMemberOrProduct() {
        member.memberProducts.add(this)
        product.memberProducts.add(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MemberProduct) return false

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