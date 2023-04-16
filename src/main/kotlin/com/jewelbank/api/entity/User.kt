package com.jewelbank.api.entity

import com.jewelbank.api.config.audit.model.AuditModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import java.util.*

@Entity
data class User(

    @Id @NotNull
    val id: String = UUID.randomUUID().toString(),

    @Email(message = "email not valid")
    @Column(nullable = false, unique = true)
    val email: String = "",

    @Column(nullable = false)
    val password: String = "",

    @Column(nullable = false)
    val name: String = "",

    @Column(length = 20)
    val phone: String = "",

    val avatarUrl: String = "",

    @Column(nullable = false, unique = true, length = 14)
    val cpf: String = "",

    ) : AuditModel(), Serializable