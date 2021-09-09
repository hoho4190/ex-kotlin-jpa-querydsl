package com.hoho.example.kotlinjpa2.repository

import com.hoho.example.kotlinjpa2.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository : JpaRepository<Member, Long>