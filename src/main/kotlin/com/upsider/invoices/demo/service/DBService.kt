package com.upsider.invoices.demo.service

import com.upsider.invoices.demo.model.db.dataobject.InvoicesDO
import com.upsider.invoices.demo.model.db.dataobject.UsersDO
import com.upsider.invoices.demo.model.db.mapper.*
import com.upsider.invoices.demo.model.request.AddInvoicesDTO
import com.upsider.invoices.demo.model.request.CreateUserDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


@Service
class DBService(val usersDOMapper: UsersDOMapper, val invoicesDOMapper: InvoicesDOMapper) {
    @Value("\${invoices.pageSize}")
    val pageSize: Int = 10
    /**
     * Get user from db by email
     *
     * @param mail email of the user
     * @return UsersDO data object of the target user
     */
    @Cacheable(key = "#mail", cacheNames = ["USER"])
    fun getUserByMail(mail: String) : UsersDO?{
        val user: UsersDO? = usersDOMapper.selectOne{
            where { UsersDODynamicSqlSupport.email isEqualTo mail }
        }
        return user;
    }

    /**
     * Insert a new user to db
     *
     * @param createUserDTO user data object
     * @return null
     */
    fun createUser(createUserDTO: CreateUserDTO){
        val hash = MessageDigest.getInstance("MD5").digest(createUserDTO.password.toByteArray(Charsets.UTF_8))
        // save password hash in db instead of password itself
        usersDOMapper.insert(
            UsersDO(
            companyName = createUserDTO.companyName,
            name = createUserDTO.name,
            email = createUserDTO.email,
            password = Base64.getEncoder().encodeToString(hash),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
        )
        )
    }

    /**
     * Insert a new invoice to DB
     *
     * @param addInvoicesDTO data object of invoice
     * @return null
     */
    fun insertInvoices(addInvoicesDTO: AddInvoicesDTO){
        invoicesDOMapper.insert(
            InvoicesDO(
                userId = addInvoicesDTO.userId,
                issueDate = LocalDate.now(),
                paymentAmount = addInvoicesDTO.paymentAmount,
                fee = addInvoicesDTO.paymentAmount*0.04.toBigDecimal(),
                feeRate = 0.04.toBigDecimal(),
                taxAmount = addInvoicesDTO.paymentAmount*0.04.toBigDecimal()*0.1.toBigDecimal(),
                taxRate = 0.1.toBigDecimal(),
                totalAmount = addInvoicesDTO.paymentAmount+addInvoicesDTO.paymentAmount*0.04.toBigDecimal()*1.1.toBigDecimal(),
                paymentDueDate = addInvoicesDTO.paymentDueDate,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now(),
            )
        )
    }

    /**
     * Insert a new invoice to DB
     *
     * @param userID data object of invoice
     * @param start start date of invoice
     * @param end end date of invoice
     * @return List<InvoicesDO>
     */
    fun getInvoicesByDate(userID:Int, start: LocalDate, end: LocalDate, page:Int?) : List<InvoicesDO> {
        val index =  page?:0
        val invoices : List<InvoicesDO> = invoicesDOMapper.select {
            where { InvoicesDODynamicSqlSupport.userId isEqualTo userID
                and {InvoicesDODynamicSqlSupport.issueDate isBetween start and end} }
            orderBy(InvoicesDODynamicSqlSupport.id)
            limit(pageSize.toLong())
            offset((index*pageSize).toLong())
        }
        return invoices;
    }
}