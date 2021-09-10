package com.hoho.example.kotlinjpa2.repository

import com.hoho.example.kotlinjpa2.dto.LockerDto
import com.hoho.example.kotlinjpa2.dto.QLockerDto
import com.hoho.example.kotlinjpa2.dto.QMemberDto
import com.hoho.example.kotlinjpa2.entity.Locker
import com.hoho.example.kotlinjpa2.entity.QLocker.locker
import com.hoho.example.kotlinjpa2.entity.QMember.member
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class LockerRepositoryImpl(
    @Autowired private val jpaQueryFactory: JPAQueryFactory
) : LockerRepositoryCustom {

    override fun findByMemberId(id: Long): Optional<Locker> {
        return Optional.ofNullable(
            jpaQueryFactory
                .selectFrom(locker)
                .rightJoin(member).on(member.locker.eq(locker))
                .where(member.id.eq(id))
                .fetchOne()
        )
    }

    override fun findDtoByMemberId(id: Long): Optional<LockerDto> {
        return Optional.ofNullable(
            jpaQueryFactory
                .select(
//                    Projections.fields()
//                    Projections.bean()
//                    Projections.constructor()
                    QLockerDto( // @QueryProjection 사용
                        locker.id,
                        locker.name,
                        QMemberDto(member.id, member.name),
                        locker.createAt,
                        locker.updateAt
                    )
                )
                .from(locker)
                .rightJoin(member).on(member.locker.eq(locker))
                .where(eqMemberId(id))
                .fetchOne()
        )
    }

    private fun eqMemberId(id: Long): BooleanExpression? {
        return member.id.eq(id)
    }

//    private fun eqName(name: String): BooleanExpression? {
//        return if (name.isEmpty()) {
//            null
//        } else locker.name.eq(name)
//    }
}