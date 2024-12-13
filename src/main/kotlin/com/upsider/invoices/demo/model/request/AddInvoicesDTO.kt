package com.upsider.invoices.demo.model.request

import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal
import java.time.LocalDate
/**
 * Data Object for creating a new Invoice
 *
 */
data class AddInvoicesDTO(
    @field:NotBlank(message = "ユーザーID")
    val userId:Int,
    @field:NotBlank(message = "支払い金額")
    val paymentAmount: BigDecimal,
    @field:NotBlank(message = "計算締切")
    val paymentDueDate: LocalDate
)
