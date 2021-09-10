package com.hoho.example.kotlinjpa2.dto

import com.hoho.example.kotlinjpa2.config.NoArgsConstructor
import com.querydsl.core.annotations.QueryProjection
import java.util.*

/**
 * DTO에 @QueryProjection 어노테이션을 사용하면 DTO도 Q파일을 생성할 수 있음
 * new QDTO로 컴파일 시점에 오류를 검사할 수 있고 어떤 인자가 생성자로 필요한지도 알기가 수월함
 * 하지만 이는 DTO가 Querydsl에 의존해야 하는 단점이 있기 때문에 선택적으로 사용할 것
 */
@NoArgsConstructor
class LockerDto @QueryProjection constructor(
    var id: Long?,
    var name: String?
) : BaseDto() {
    var member: MemberDto? = null

    @QueryProjection
    constructor (id: Long?, name: String?, member: MemberDto?, createAt: Date, updateAt: Date) : this(id, name) {
        this.member = member
        this.createAt = createAt
        this.updateAt = updateAt
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
        return "Locker(id=$id, name='$name', ${super.toString()})"
    }
}