package com.upsider.invoices.demo.model.db.mapper

import com.upsider.invoices.demo.model.db.dataobject.InvoicesDO
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.createdAt
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.fee
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.feeRate
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.id
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.invoicesDO
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.issueDate
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.paymentAmount
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.paymentDueDate
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.taxAmount
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.taxRate
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.totalAmount
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.updatedAt
import com.upsider.invoices.demo.model.db.mapper.InvoicesDODynamicSqlSupport.userId
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter
import org.mybatis.dynamic.sql.util.kotlin.CountCompleter
import org.mybatis.dynamic.sql.util.kotlin.DeleteCompleter
import org.mybatis.dynamic.sql.util.kotlin.KotlinUpdateBuilder
import org.mybatis.dynamic.sql.util.kotlin.SelectCompleter
import org.mybatis.dynamic.sql.util.kotlin.UpdateCompleter
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.countFrom
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.deleteFrom
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insert
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.insertMultiple
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectDistinct
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectList
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.selectOne
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.update
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper

@Mapper
interface InvoicesDOMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<InvoicesDO>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="InvoicesDOResult", value = [
        Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER),
        Result(column="issue_date", property="issueDate", jdbcType=JdbcType.DATE),
        Result(column="payment_amount", property="paymentAmount", jdbcType=JdbcType.DECIMAL),
        Result(column="fee", property="fee", jdbcType=JdbcType.DECIMAL),
        Result(column="fee_rate", property="feeRate", jdbcType=JdbcType.DECIMAL),
        Result(column="tax_amount", property="taxAmount", jdbcType=JdbcType.DECIMAL),
        Result(column="tax_rate", property="taxRate", jdbcType=JdbcType.DECIMAL),
        Result(column="total_amount", property="totalAmount", jdbcType=JdbcType.DECIMAL),
        Result(column="payment_due_date", property="paymentDueDate", jdbcType=JdbcType.DATE),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<InvoicesDO>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("InvoicesDOResult")
    fun selectOne(selectStatement: SelectStatementProvider): InvoicesDO?
}

fun InvoicesDOMapper.count(completer: CountCompleter) =
    countFrom(this::count, invoicesDO, completer)

fun InvoicesDOMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, invoicesDO, completer)

fun InvoicesDOMapper.deleteByPrimaryKey(id_: Long) =
    delete {
        where { id isEqualTo id_ }
    }

fun InvoicesDOMapper.insert(row: InvoicesDO) =
    insert(this::insert, row, invoicesDO) {
        map(id) toProperty "id"
        map(userId) toProperty "userId"
        map(issueDate) toProperty "issueDate"
        map(paymentAmount) toProperty "paymentAmount"
        map(fee) toProperty "fee"
        map(feeRate) toProperty "feeRate"
        map(taxAmount) toProperty "taxAmount"
        map(taxRate) toProperty "taxRate"
        map(totalAmount) toProperty "totalAmount"
        map(paymentDueDate) toProperty "paymentDueDate"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun InvoicesDOMapper.insertMultiple(records: Collection<InvoicesDO>) =
    insertMultiple(this::insertMultiple, records, invoicesDO) {
        map(id) toProperty "id"
        map(userId) toProperty "userId"
        map(issueDate) toProperty "issueDate"
        map(paymentAmount) toProperty "paymentAmount"
        map(fee) toProperty "fee"
        map(feeRate) toProperty "feeRate"
        map(taxAmount) toProperty "taxAmount"
        map(taxRate) toProperty "taxRate"
        map(totalAmount) toProperty "totalAmount"
        map(paymentDueDate) toProperty "paymentDueDate"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun InvoicesDOMapper.insertMultiple(vararg records: InvoicesDO) =
    insertMultiple(records.toList())

fun InvoicesDOMapper.insertSelective(row: InvoicesDO) =
    insert(this::insert, row, invoicesDO) {
        map(id).toPropertyWhenPresent("id", row::id)
        map(userId).toPropertyWhenPresent("userId", row::userId)
        map(issueDate).toPropertyWhenPresent("issueDate", row::issueDate)
        map(paymentAmount).toPropertyWhenPresent("paymentAmount", row::paymentAmount)
        map(fee).toPropertyWhenPresent("fee", row::fee)
        map(feeRate).toPropertyWhenPresent("feeRate", row::feeRate)
        map(taxAmount).toPropertyWhenPresent("taxAmount", row::taxAmount)
        map(taxRate).toPropertyWhenPresent("taxRate", row::taxRate)
        map(totalAmount).toPropertyWhenPresent("totalAmount", row::totalAmount)
        map(paymentDueDate).toPropertyWhenPresent("paymentDueDate", row::paymentDueDate)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(id, userId, issueDate, paymentAmount, fee, feeRate, taxAmount, taxRate, totalAmount, paymentDueDate, createdAt, updatedAt)

fun InvoicesDOMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, invoicesDO, completer)

fun InvoicesDOMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, invoicesDO, completer)

fun InvoicesDOMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, invoicesDO, completer)

fun InvoicesDOMapper.selectByPrimaryKey(id_: Long) =
    selectOne {
        where { id isEqualTo id_ }
    }

fun InvoicesDOMapper.update(completer: UpdateCompleter) =
    update(this::update, invoicesDO, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: InvoicesDO) =
    apply {
        set(id) equalToOrNull row::id
        set(userId) equalToOrNull row::userId
        set(issueDate) equalToOrNull row::issueDate
        set(paymentAmount) equalToOrNull row::paymentAmount
        set(fee) equalToOrNull row::fee
        set(feeRate) equalToOrNull row::feeRate
        set(taxAmount) equalToOrNull row::taxAmount
        set(taxRate) equalToOrNull row::taxRate
        set(totalAmount) equalToOrNull row::totalAmount
        set(paymentDueDate) equalToOrNull row::paymentDueDate
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: InvoicesDO) =
    apply {
        set(id) equalToWhenPresent row::id
        set(userId) equalToWhenPresent row::userId
        set(issueDate) equalToWhenPresent row::issueDate
        set(paymentAmount) equalToWhenPresent row::paymentAmount
        set(fee) equalToWhenPresent row::fee
        set(feeRate) equalToWhenPresent row::feeRate
        set(taxAmount) equalToWhenPresent row::taxAmount
        set(taxRate) equalToWhenPresent row::taxRate
        set(totalAmount) equalToWhenPresent row::totalAmount
        set(paymentDueDate) equalToWhenPresent row::paymentDueDate
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun InvoicesDOMapper.updateByPrimaryKey(row: InvoicesDO) =
    update {
        set(userId) equalToOrNull row::userId
        set(issueDate) equalToOrNull row::issueDate
        set(paymentAmount) equalToOrNull row::paymentAmount
        set(fee) equalToOrNull row::fee
        set(feeRate) equalToOrNull row::feeRate
        set(taxAmount) equalToOrNull row::taxAmount
        set(taxRate) equalToOrNull row::taxRate
        set(totalAmount) equalToOrNull row::totalAmount
        set(paymentDueDate) equalToOrNull row::paymentDueDate
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { id isEqualTo row.id!! }
    }

fun InvoicesDOMapper.updateByPrimaryKeySelective(row: InvoicesDO) =
    update {
        set(userId) equalToWhenPresent row::userId
        set(issueDate) equalToWhenPresent row::issueDate
        set(paymentAmount) equalToWhenPresent row::paymentAmount
        set(fee) equalToWhenPresent row::fee
        set(feeRate) equalToWhenPresent row::feeRate
        set(taxAmount) equalToWhenPresent row::taxAmount
        set(taxRate) equalToWhenPresent row::taxRate
        set(totalAmount) equalToWhenPresent row::totalAmount
        set(paymentDueDate) equalToWhenPresent row::paymentDueDate
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { id isEqualTo row.id!! }
    }