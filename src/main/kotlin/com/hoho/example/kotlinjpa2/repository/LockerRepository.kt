package com.hoho.example.kotlinjpa2.repository

import com.hoho.example.kotlinjpa2.entity.Locker
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LockerRepository : JpaRepository<Locker, Long>, LockerRepositoryCustom {

    fun findAllByName(name: String): MutableList<Locker>
}