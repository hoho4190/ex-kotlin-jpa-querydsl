package com.hoho.example.jpa.repository

import com.hoho.example.jpa.entity.MemberProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberProductRepository : JpaRepository<MemberProduct, Long>