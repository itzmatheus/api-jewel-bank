package com.jewelbank.api.utils

import java.time.LocalDate
import kotlin.random.Random

fun generateAccountNumber(): String {
    return System.currentTimeMillis().toString().substring(6, 12) + (111..999).random().toString()
}

fun generateCardNumber(): String {
    return  "${(4000..5599).random()}-${(4000..5599).random()}-${(4000..5599).random()}-${(4000..5599).random()}"
}

fun generateCardExpireDate(): String {
    val today = LocalDate.now()
    return "${today.month.value.toString().padStart(2, '0')}/${today.year+7}"
}

fun generateCardSecureNumber(): String {
    return (111..999).random().toString()
}

fun generateLimitForClient(): Double {
    return Random.nextDouble(500.00, 3500.00)
}
