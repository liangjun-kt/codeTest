package com.upsider.invoices.demo.model.db.dataobject

import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class InvoicesDO(
    var id: Long? = null,
    var userId: Int? = null,
    var issueDate: LocalDate? = null,
    var paymentAmount: BigDecimal? = null,
    var fee: BigDecimal? = null,
    var feeRate: BigDecimal? = null,
    var taxAmount: BigDecimal? = null,
    var taxRate: BigDecimal? = null,
    var totalAmount: BigDecimal? = null,
    var paymentDueDate: LocalDate? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) : Serializable