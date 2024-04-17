package com.spliticket.spliticket_api.embeds

import jakarta.persistence.Embeddable

@Embeddable
data class Price(
    val amount: Float,
    val currency: String
) {
    override fun toString() = "$amount $currency"
}