package com.hoho.example.kotlinjpa2.repository

import com.hoho.example.kotlinjpa2.entity.Team
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeamRepository : JpaRepository<Team, Long>