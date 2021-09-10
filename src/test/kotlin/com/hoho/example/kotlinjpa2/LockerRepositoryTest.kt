package com.hoho.example.kotlinjpa2

import com.hoho.example.kotlinjpa2.config.JpaTestConfig
import com.hoho.example.kotlinjpa2.entity.Locker
import com.hoho.example.kotlinjpa2.entity.Member
import com.hoho.example.kotlinjpa2.entity.Team
import com.hoho.example.kotlinjpa2.repository.LockerRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.context.annotation.Import
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles

@DisplayName("Repository Test - Locker")
@ActiveProfiles("test")
@DataJpaTest
@Import(JpaTestConfig::class) // No qualifying bean of type JPAQueryFactory 에러 해결
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class LockerRepositoryTest(
    @Autowired private val em: TestEntityManager,
    @Autowired private val lockerRepo: LockerRepository
) {
    private val mockTeamList = mutableListOf<Team>()
    private val mockLockerList = mutableListOf<Locker>()
    private val mockMemberList = mutableListOf<Member>()

    @BeforeEach
    fun setup() {
        val locker1 = Locker(1, "사물함1")
        val locker2 = Locker(2, "사물함2")
        val locker3 = Locker(3, "사물함3")
        val locker4 = Locker(4, "사물함4")
        mockLockerList.addAll(mutableListOf(locker1, locker2, locker3, locker4))
        mockLockerList.forEach { em.persist(it) }

        val team1 = Team(1, "팀1")
        mockTeamList.addAll(mutableListOf(team1))
        mockTeamList.forEach { em.persist(it) }

        val member1 = Member(1, "멤버1", team1, locker1)
        mockMemberList.addAll(mutableListOf(member1))
        mockMemberList.forEach { em.persist(it) }

        em.flush()
        em.clear()

        println("============================================================")
        println("============================================================")
        println("============================================================")
    }

    @Test
    @DisplayName("JpaRepository 테스트")
    fun testJpaRepository() {
        // given
        val mockLocker = mockLockerList[0]

        // when
        val locker1List = lockerRepo.findAll()
        val locker1 = lockerRepo.findByIdOrNull(mockLocker.id)    // 캐시 조회
        val locker2List = lockerRepo.findAllByName(mockLocker.name)
        println(locker1)

        // then
        Assertions.assertAll(
            { Assertions.assertEquals(mockLockerList.size, locker1List.size) },
            { Assertions.assertNotNull(locker1) },
            { Assertions.assertEquals(mockLocker.id, locker1!!.id) },
            { Assertions.assertTrue(locker2List.size > 0) },
            { Assertions.assertEquals(mockLocker.name, locker2List[0].name) }
        )
    }

    @Test
    @DisplayName("Custom Repository 테스트")
    fun testCustomRepository() {
        // given
        val mockMember = mockMemberList[0]

        // when
        val locker1List = lockerRepo.findAll()
        val locker1 = lockerRepo.findByMemberId(mockMember.id)  // 쿼리 질의(jpql은 캐시 조회 먼저 안함)
        println(locker1)

        // then
        Assertions.assertAll(
            { Assertions.assertEquals(mockLockerList.size, locker1List.size) },
            { Assertions.assertNotNull(locker1.orElse(null)) }
        )
    }

    @Test
    @DisplayName("Persistence Context 테스트")
    fun testPersistenceContext() {
        // given
        val mockLocker = mockLockerList[0]
        val mockMember = mockMemberList[0]

        // when
        val lockerList = lockerRepo.findAll()  // locker 목록들이 영속화됨
        val locker1 = lockerRepo.findByIdOrNull(mockLocker.id)  // 1차 캐시에서 조회
        lockerList[1].name = "이름 변경 2" // 커밋 시점에 업데이트 쿼리가 생겨야 하지만 jpql은 실행 전 flush 동작함
        val locker2 = lockerRepo.findByMemberId(mockMember.id).orElse(null)  // 쿼리 질의(jpql은 캐시 조회 먼저 안함)
        println(locker1)
        println(locker2)

        // then
        Assertions.assertSame(locker1, locker2)

        println("===== 테스트 끝")
        em.flush()
    }

    /**
     * 대량의 데이터 조회가 필요하거나 성능 최적화를 고강도로 하고 싶은 경우 Dto 조회를 사용
     */
    @Test
    @DisplayName("Custom Repository 테스트 - DTO로 리턴")
    fun testReturnDto() {
        // given
        val mockMember = mockMemberList[0]

        // when
        val lockerDto = lockerRepo.findDtoByMemberId(mockMember.id).orElse(null)
        println(lockerDto)
        println(lockerDto.member)

        // then
        Assertions.assertAll(
            { Assertions.assertNotNull(lockerDto) }
        )
    }
}