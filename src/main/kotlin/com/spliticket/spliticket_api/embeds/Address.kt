package com.spliticket.spliticket_api.embeds

import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    val street: String,
    val houseNumber: String,
    val city: String,
    val postalCode: String,
    val country: String
)