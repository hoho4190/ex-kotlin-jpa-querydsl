package com.hoho.example.jpa.repository

import com.hoho.example.jpa.dto.LockerDto
import com.hoho.example.jpa.entity.Locker
import java.util.*

interface LockerRepositoryCustom {
    fun findByMemberId(id: Long): Optional<Locker>

    /**
     * 대량의 데이터 조회가 필요하거나 성능 최적화를 고강도로 하고 싶은 경우 Dto 조회를 사용
     */
    fun findDtoByMemberId(id: Long): Optional<LockerDto>
}