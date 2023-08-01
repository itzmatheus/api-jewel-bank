package com.jewelbank.api.entity

import com.jewelbank.api.config.audit.model.AuditModel
import com.jewelbank.api.entity.enumeration.Flag
import com.jewelbank.api.entity.enumeration.TypeCard
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.util.*

@Entity
data class Card(

    @Id @NotNull
    val id: String = UUID.randomUUID().toString(),

    @Column(nullable = false)
    val name: String = "",

    @Column(nullable = false)
    val number: String = "",

    @Column(nullable = false, length = 6)
    val expireDate: String = "",

    @Column(nullable = false, length = 4)
    val secureNumber: String = "",

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val flag: Flag? = null,

    @Column(nullable = false, length = 255)
    @Enumerated(EnumType.STRING)
    val type: TypeCard? = null,

    @Column(nullable = false)
    val limit: Double = 0.0,

    @Column(nullable = false)
    val contactless: Boolean = false,

    @Column(nullable = false)
    val onlinePayment: Boolean = false,

    @Column(nullable = false)
    val atmWithdraws: Boolean = false,

    @ManyToOne
    @JoinColumn(name = "fk_account_id")
    val account: Account? = null,

    ) : AuditModel(), Serializable