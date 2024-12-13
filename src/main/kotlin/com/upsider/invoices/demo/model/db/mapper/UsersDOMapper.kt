package com.upsider.invoices.demo.model.db.mapper

import com.upsider.invoices.demo.model.db.dataobject.UsersDO
import com.upsider.invoices.demo.model.db.mapper.UsersDODynamicSqlSupport.companyName
import com.upsider.invoices.demo.model.db.mapper.UsersDODynamicSqlSupport.createdAt
import com.upsider.invoices.demo.model.db.mapper.UsersDODynamicSqlSupport.email
import com.upsider.invoices.demo.model.db.mapper.UsersDODynamicSqlSupport.id
import com.upsider.invoices.demo.model.db.mapper.UsersDODynamicSqlSupport.name
import com.upsider.invoices.demo.model.db.mapper.UsersDODynamicSqlSupport.password
import com.upsider.invoices.demo.model.db.mapper.UsersDODynamicSqlSupport.updatedAt
import com.upsider.invoices.demo.model.db.mapper.UsersDODynamicSqlSupport.usersDO
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
interface UsersDOMapper : CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<UsersDO>, CommonUpdateMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="UsersDOResult", value = [
        Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        Result(column="company_name", property="companyName", jdbcType=JdbcType.VARCHAR),
        Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        Result(column="created_at", property="createdAt", jdbcType=JdbcType.TIMESTAMP),
        Result(column="updated_at", property="updatedAt", jdbcType=JdbcType.TIMESTAMP)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<UsersDO>

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("UsersDOResult")
    fun selectOne(selectStatement: SelectStatementProvider): UsersDO?
}

fun UsersDOMapper.count(completer: CountCompleter) =
    countFrom(this::count, usersDO, completer)

fun UsersDOMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, usersDO, completer)

fun UsersDOMapper.deleteByPrimaryKey(id_: Long) =
    delete {
        where { id isEqualTo id_ }
    }

fun UsersDOMapper.insert(row: UsersDO) =
    insert(this::insert, row, usersDO) {
        map(id) toProperty "id"
        map(companyName) toProperty "companyName"
        map(name) toProperty "name"
        map(email) toProperty "email"
        map(password) toProperty "password"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun UsersDOMapper.insertMultiple(records: Collection<UsersDO>) =
    insertMultiple(this::insertMultiple, records, usersDO) {
        map(id) toProperty "id"
        map(companyName) toProperty "companyName"
        map(name) toProperty "name"
        map(email) toProperty "email"
        map(password) toProperty "password"
        map(createdAt) toProperty "createdAt"
        map(updatedAt) toProperty "updatedAt"
    }

fun UsersDOMapper.insertMultiple(vararg records: UsersDO) =
    insertMultiple(records.toList())

fun UsersDOMapper.insertSelective(row: UsersDO) =
    insert(this::insert, row, usersDO) {
        map(id).toPropertyWhenPresent("id", row::id)
        map(companyName).toPropertyWhenPresent("companyName", row::companyName)
        map(name).toPropertyWhenPresent("name", row::name)
        map(email).toPropertyWhenPresent("email", row::email)
        map(password).toPropertyWhenPresent("password", row::password)
        map(createdAt).toPropertyWhenPresent("createdAt", row::createdAt)
        map(updatedAt).toPropertyWhenPresent("updatedAt", row::updatedAt)
    }

private val columnList = listOf(id, companyName, name, email, password, createdAt, updatedAt)

fun UsersDOMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, usersDO, completer)

fun UsersDOMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, usersDO, completer)

fun UsersDOMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, usersDO, completer)

fun UsersDOMapper.selectByPrimaryKey(id_: Long) =
    selectOne {
        where { id isEqualTo id_ }
    }

fun UsersDOMapper.update(completer: UpdateCompleter) =
    update(this::update, usersDO, completer)

fun KotlinUpdateBuilder.updateAllColumns(row: UsersDO) =
    apply {
        set(id) equalToOrNull row::id
        set(companyName) equalToOrNull row::companyName
        set(name) equalToOrNull row::name
        set(email) equalToOrNull row::email
        set(password) equalToOrNull row::password
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(row: UsersDO) =
    apply {
        set(id) equalToWhenPresent row::id
        set(companyName) equalToWhenPresent row::companyName
        set(name) equalToWhenPresent row::name
        set(email) equalToWhenPresent row::email
        set(password) equalToWhenPresent row::password
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
    }

fun UsersDOMapper.updateByPrimaryKey(row: UsersDO) =
    update {
        set(companyName) equalToOrNull row::companyName
        set(name) equalToOrNull row::name
        set(email) equalToOrNull row::email
        set(password) equalToOrNull row::password
        set(createdAt) equalToOrNull row::createdAt
        set(updatedAt) equalToOrNull row::updatedAt
        where { id isEqualTo row.id!! }
    }

fun UsersDOMapper.updateByPrimaryKeySelective(row: UsersDO) =
    update {
        set(companyName) equalToWhenPresent row::companyName
        set(name) equalToWhenPresent row::name
        set(email) equalToWhenPresent row::email
        set(password) equalToWhenPresent row::password
        set(createdAt) equalToWhenPresent row::createdAt
        set(updatedAt) equalToWhenPresent row::updatedAt
        where { id isEqualTo row.id!! }
    }