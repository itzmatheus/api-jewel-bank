package com.jewelbank.api.repository

import com.jewelbank.api.entity.Agency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AgencyRepository: JpaRepository<Agency, String> {
    fun findByOrderByCreatedAtDesc(): List<Agency>
}