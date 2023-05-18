package com.jewelbank.api.config.audit.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Temporal
import jakarta.persistence.TemporalType
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.util.Date

@MappedSuperclass
@JsonIgnoreProperties(value = ["createdAt", "updatedAt"])
@EntityListeners(AuditingEntityListener::class)
abstract class AuditModel: Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    open var createdAt: Date? = null

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    open var updatedAt: Date? = null
}