package com.jewelbank.api.service

import com.jewelbank.api.entity.Agency
import com.jewelbank.api.repository.AgencyRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AgencyService(
    private val agencyRepository: AgencyRepository,
) {

    private val logger = LoggerFactory.getLogger(AgencyService::class.java)

    fun getAgency(): Agency? {
        logger.info("Getting agency")
        return agencyRepository.findByOrderByCreatedAtDesc().first()
    }

}