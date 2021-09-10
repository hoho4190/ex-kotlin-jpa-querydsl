package com.hoho.example.jpa.repository

import com.hoho.example.jpa.entity.Locker
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LockerRepository : JpaRepository<Locker, Long>, LockerRepositoryCustom {

    fun findAllByName(name: String): MutableList<Locker>
}