package com.upsider.invoices.demo.model.db.dataobject

import java.io.Serializable
import java.time.LocalDateTime

data class UsersDO(
    var id: Long? = null,
    var companyName: String? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) : Serializable