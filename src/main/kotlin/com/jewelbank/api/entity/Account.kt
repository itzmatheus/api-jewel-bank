package com.jewelbank.api.entity

import com.jewelbank.api.config.audit.model.AuditModel
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.util.*

@Entity
data class Account(

    @Id @NotNull
    val id: String = UUID.randomUUID().toString(),

    @ManyToOne
    @JoinColumn(name = "fk_bank_user_id")
    val bankUser: BankUser? = null,

    @ManyToOne
    @JoinColumn(name = "fk_bank_id")
    val bank: Bank? = null,

    @ManyToOne
    @JoinColumn(name = "fk_agency_id")
    val agency: Agency? = null,

    @Column(nullable = false, length = 8)
    val number: String = "",

    ) : AuditModel(), Serializable