package com.hoho.example.kotlinjpa2.service

import com.hoho.example.kotlinjpa2.entity.Locker

interface LockerService {
    fun get(id: Long): Locker?
    fun getList(): MutableList<Locker>
    fun save(locker: Locker): Locker
    fun delete(id: Long)
}