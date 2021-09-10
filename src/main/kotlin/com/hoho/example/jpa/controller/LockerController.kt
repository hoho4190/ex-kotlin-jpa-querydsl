package com.hoho.example.jpa.controller

import com.hoho.example.jpa.dto.LockerSaveRequestDto
import com.hoho.example.jpa.entity.Locker
import com.hoho.example.jpa.service.LockerService
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/locker")
class LockerController(
    @Autowired private val lockerService: LockerService
) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Locker? = lockerService.get(id)

    @GetMapping("/list")
    fun getList(): MutableList<Locker> = lockerService.getList()

    @PostMapping("")
    fun save(@Valid @RequestBody request: LockerSaveRequestDto): Locker =
        lockerService.save(Locker(request.id, request.name))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): Unit = lockerService.delete(id)
}