package com.jewelbank.api.entity

import com.jewelbank.api.config.audit.model.AuditModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.util.*

@Entity
data class Agency(

    @Id @NotNull
    val id: String = UUID.randomUUID().toString(),

    @Column(nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var number: String = "",

    ) : AuditModel(), Serializable