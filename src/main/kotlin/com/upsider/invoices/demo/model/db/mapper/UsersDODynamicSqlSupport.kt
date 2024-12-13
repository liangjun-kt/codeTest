package com.upsider.invoices.demo.model.db.mapper

import java.sql.JDBCType
import java.time.LocalDateTime
import org.mybatis.dynamic.sql.AliasableSqlTable
import org.mybatis.dynamic.sql.util.kotlin.elements.column

object UsersDODynamicSqlSupport {
    val usersDO = UsersDO()

    val id = usersDO.id

    val companyName = usersDO.companyName

    val name = usersDO.name

    val email = usersDO.email

    val password = usersDO.password

    val createdAt = usersDO.createdAt

    val updatedAt = usersDO.updatedAt

    class UsersDO : AliasableSqlTable<UsersDO>("users", ::UsersDO) {
        val id = column<Long>(name = "id", jdbcType = JDBCType.BIGINT)

        val companyName = column<String>(name = "company_name", jdbcType = JDBCType.VARCHAR)

        val name = column<String>(name = "name", jdbcType = JDBCType.VARCHAR)

        val email = column<String>(name = "email", jdbcType = JDBCType.VARCHAR)

        val password = column<String>(name = "password", jdbcType = JDBCType.VARCHAR)

        val createdAt = column<LocalDateTime>(name = "created_at", jdbcType = JDBCType.TIMESTAMP)

        val updatedAt = column<LocalDateTime>(name = "updated_at", jdbcType = JDBCType.TIMESTAMP)
    }
}