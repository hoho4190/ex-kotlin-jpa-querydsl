package com.hoho.example.jpa

import com.hoho.example.jpa.entity.Locker
import com.hoho.example.jpa.repository.LockerRepository
import com.hoho.example.jpa.service.impl.LockerServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.test.context.ActiveProfiles
import java.util.*

@DisplayName("Service Test - Locker")
@ActiveProfiles("test")
@ExtendWith(MockitoExtension::class)
class LockerServiceTest {

    @Mock
    private lateinit var repo: LockerRepository

    @InjectMocks
    private lateinit var service: LockerServiceImpl

    private val mockLockerList = mutableListOf<Locker>()

    @BeforeEach
    fun setup() {
        mockLockerList.add(Locker(101, "locker101"))
        mockLockerList.add(Locker(102, "locker102"))
    }

    @Test
    @DisplayName("get")
    fun get() {
        // given
        val mockLocker = mockLockerList[0]
        given(repo.findById(mockLocker.id)).willReturn(Optional.of(mockLocker))

        // when
        val locker = service.get(mockLocker.id)

        // then
        Assertions.assertEquals(mockLocker.id, locker?.id)
    }

    @Test
    @DisplayName("getList")
    fun getList() {
        // given
        given(repo.findAll()).willReturn(mockLockerList)

        // when
        val list = service.getList()

        // then
        Assertions.assertEquals(mockLockerList.size, list.size)
    }

    @Test
    @DisplayName("save")
    fun save() {
        // given
        given(repo.save(any(Locker::class.java))).will {
            val input = it.arguments[0] as Locker
            val output = Locker(input.id, input.name)

            output
        }

        // when
        val mockLocker = Locker(103, "locker103")
        val savedLocker = service.save(mockLocker)

        // then
        Assertions.assertEquals(mockLocker.id, savedLocker.id)
        Assertions.assertNotSame(mockLocker, savedLocker)
    }

    @Test
    @DisplayName("delete")
    fun delete() {
        // given
        val listIdx = 0
        val id = mockLockerList[listIdx].id
        given(repo.deleteById(id)).will {
            mockLockerList.removeAt(listIdx)
            Unit
        }

        // when
        service.delete(id)

        // then
        Assertions.assertNotEquals(2, mockLockerList.size)
    }

}