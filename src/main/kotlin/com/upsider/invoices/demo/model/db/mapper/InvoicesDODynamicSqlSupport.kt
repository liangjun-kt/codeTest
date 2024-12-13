package com.upsider.invoices.demo.model.db.mapper

import java.math.BigDecimal
import java.sql.JDBCType
import java.time.LocalDate
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object InvoicesDODynamicSqlSupport {
    val invoicesDO = InvoicesDO()

    val id = invoicesDO.id

    val userId = invoicesDO.userId

    val issueDate = invoicesDO.issueDate

    val paymentAmount = invoicesDO.paymentAmount

    val fee = invoicesDO.fee

    val feeRate = invoicesDO.feeRate

    val taxAmount = invoicesDO.taxAmount

    val taxRate = invoicesDO.taxRate

    val totalAmount = invoicesDO.totalAmount

    val paymentDueDate = invoicesDO.paymentDueDate

    val createdAt = invoicesDO.createdAt

    val updatedAt = invoicesDO.updatedAt

    class InvoicesDO : AliasableSqlTable<InvoicesDO>("invoices", ::InvoicesDO) {
        val id = column<Long>(name = "id", jdbcType = JDBCType.BIGINT)

        val userId = column<Int>(name = "user_id", jdbcType = JDBCType.INTEGER)

        val issueDate = column<LocalDate>(name = "issue_date", jdbcType = JDBCType.DATE)

        val paymentAmount = column<BigDecimal>(name = "payment_amount", jdbcType = JDBCType.DECIMAL)

        val fee = column<BigDecimal>(name = "fee", jdbcType = JDBCType.DECIMAL)

        val feeRate = column<BigDecimal>(name = "fee_rate", jdbcType = JDBCType.DECIMAL)

        val taxAmount = column<BigDecimal>(name = "tax_amount", jdbcType = JDBCType.DECIMAL)

        val taxRate = column<BigDecimal>(name = "tax_rate", jdbcType = JDBCType.DECIMAL)

        val totalAmount = column<BigDecimal>(name = "total_amount", jdbcType = JDBCType.DECIMAL)

        val paymentDueDate = column<LocalDate>(name = "payment_due_date", jdbcType = JDBCType.DATE)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}