package com.hoho.example.jpa

import com.fasterxml.jackson.databind.ObjectMapper
import com.hoho.example.jpa.controller.LockerController
import com.hoho.example.jpa.entity.Locker
import com.hoho.example.jpa.service.LockerService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.kotlin.any
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.springframework.web.filter.CharacterEncodingFilter

@DisplayName("Controller Test - Locker")
@ActiveProfiles("test")
@WebMvcTest(controllers = [LockerController::class])
class LockerControllerTest {

    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var service: LockerService

    private val mockLockerList = mutableListOf<Locker>()
    private val om = ObjectMapper()

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(LockerController(service))
            .addFilters<StandaloneMockMvcBuilder>(
                CharacterEncodingFilter("UTF-8", true)) // 한글 깨짐 설정
            .alwaysDo<StandaloneMockMvcBuilder>(MockMvcResultHandlers.print())  // MockMvcResult 정보 print
            .build()

        mockLockerList.add(Locker(101, "locker101"))
        mockLockerList.add(Locker(102, "locker102"))
    }

    @Test
    @DisplayName("get")
    fun getTest() {
        // given
        val mockLocker = mockLockerList[0]
        given(service.get(mockLocker.id)).willReturn(mockLocker)

        // when
        val actions = mockMvc.perform(get("/locker/{id}", mockLocker.id))

        // then
        actions
            .andExpect(status().isOk) // HttpStatus.OK(200)
            .andExpect(jsonPath("id").value(mockLocker.id))
//            .andExpect(content().string(om.writeValueAsString(mockLocker)))
            .andDo {
                val content = it.response.contentAsString
                Assertions.assertEquals(content.length, om.writeValueAsString(mockLocker).length)
            }

//        val mvcResult: MvcResult = actions.andExpect(status().isOk).andReturn()
//        val content = mvcResult.response.contentAsString
//        println("content: $content")
//        Assertions.assertEquals()
    }

    @Test
    @DisplayName("getList")
    fun getList() {
        // given
        given(service.getList()).willReturn(mockLockerList)

        // when
        val actions = mockMvc.perform(get("/locker/list"))

        // then
        actions
            .andExpect(status().isOk)
    }

    @Test
    @DisplayName("save")
    fun save() {
        // given
        given(service.save(any())).will {
            val input = it.arguments[0] as Locker
            val output = Locker(input.id, input.name)

            output
        }

        // when
        val mockLocker = Locker(103, "locker103")
        val actions = mockMvc.perform(
            post("/locker")
                .content(om.writeValueAsString(mockLocker))
                .contentType(MediaType.APPLICATION_JSON))

        // then
        actions
            .andExpect(status().isOk)
    }

    @Test
    @DisplayName("delete")
    fun deleteTest() {
        // when
        val id = mockLockerList[0].id
        val actions = mockMvc.perform(delete("/locker/{id}", id))

        // then
        actions
            .andExpect(status().isOk)
    }
}