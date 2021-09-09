package com.hoho.example.kotlinjpa2.repository

import com.hoho.example.kotlinjpa2.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long>