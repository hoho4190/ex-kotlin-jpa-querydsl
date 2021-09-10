package com.hoho.example.jpa.service

import com.hoho.example.jpa.entity.Locker

interface LockerService {
    fun get(id: Long): Locker?
    fun getList(): MutableList<Locker>
    fun save(locker: Locker): Locker
    fun delete(id: Long)
}