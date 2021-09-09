package com.hoho.example.kotlinjpa2.service.impl

import com.hoho.example.kotlinjpa2.entity.Locker
import com.hoho.example.kotlinjpa2.repository.LockerRepository
import com.hoho.example.kotlinjpa2.service.LockerService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}

@Service
class LockerServiceImpl(
    @Autowired private val lockerRepo: LockerRepository
) : LockerService {
    override fun get(id: Long): Locker? = lockerRepo.findByIdOrNull(id)

    override fun getList(): MutableList<Locker> = lockerRepo.findAll()

    override fun save(locker: Locker): Locker = lockerRepo.save(locker)

    override fun delete(id: Long) = lockerRepo.deleteById(id)
}