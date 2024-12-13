package com.upsider.invoices.demo.service

import com.upsider.invoices.demo.model.db.dataobject.InvoicesDO
import com.upsider.invoices.demo.model.request.AddInvoicesDTO
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class InvoicesService(val authService: AuthService, val dbService: DBService) {

    /**
     * Service level function insert new invoice to db
     *
     * @param addInvoicesDTO data object from controller
     * @return null
     */
    fun insertInvoices(addInvoicesDTO: AddInvoicesDTO){
        dbService.insertInvoices(addInvoicesDTO);
    }

    /**
     * Service level function list all invoices between start date and end date
     *
     * @param userID The username of the staff member.
     * @param from start date
     * @param to end date
     * @return List<InvoicesDO>
     */
    fun listUpInvoices(userID:String, from: LocalDate, to:LocalDate):List<InvoicesDO>{
        if (userID.isNotBlank()){
            return dbService.getInvoicesByDate(userID.toInt(), from, to)
        }else{
            throw Exception("Token Expired! Please use /api/auth/login to login first");
        }
    }
}