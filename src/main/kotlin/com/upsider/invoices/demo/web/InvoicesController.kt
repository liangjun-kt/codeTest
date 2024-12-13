package com.upsider.invoices.demo.web

import com.upsider.invoices.demo.model.request.AddInvoicesDTO
import com.upsider.invoices.demo.service.AuthService
import com.upsider.invoices.demo.service.InvoicesService
import jakarta.validation.Valid
import org.jetbrains.annotations.NotNull
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api")
class InvoicesController(val authService: AuthService,
                         val invoicesService: InvoicesService) {

    /**
     * Create a new invoice api
     *
     * @param authorization bearer token from request header
     * @param addInvoicesDTO data object for new invoice
     * @return ResponseEntity
     */
    @PostMapping("/invoices")
    fun addNewInvoices(@RequestHeader(value="Authorization") authorization:String,
                       @Valid @RequestBody addInvoicesDTO: AddInvoicesDTO, ):ResponseEntity<Any>{
        val accessToken = this.getTokenFromHeader(authorization)
        authService.validTokenAction(accessToken, addInvoicesDTO.userId.toString())
        invoicesService.insertInvoices(addInvoicesDTO)
        return ResponseEntity.ok("")
    }

    /**
     * get related invoices for current user
     *
     * @param authorization bearer token from request header
     * @param from from date of the query
     * @param to end date of the query
     * @param pageIndex result will return at max ${invoices.pageSize} records in each response
     *                  cause the target records will be tons of data, and not all could be returned
     *                  invoices.pageSize now is only 5, for just test, in prod env it could raise to 100 or more
     * @return ResponseEntity<List<InvoicesDO>>
     */
    @GetMapping("/invoices")
    fun listAllInvoices(@RequestHeader(value="Authorization") authorization:String,
                        @NotNull @RequestParam from: String,
                        @NotNull @RequestParam to: String,
                        @RequestParam pageIndex:Int):ResponseEntity<Any>{

        val accessToken = this.getTokenFromHeader(authorization)
        val userID = authService.validToken(accessToken)
        val start:LocalDate
        val end:LocalDate
        try {
            start = LocalDate.parse(from)
            end = LocalDate.parse(to)
        }catch (ex:Exception){
            throw Exception("Error to parse start date and end date, please try again");
        }
        val list = invoicesService.listUpInvoices(userID.toString(),
            start, end, pageIndex)
        return ResponseEntity.ok(list)
    }

    private fun getTokenFromHeader(authorization: String): String{
        if(authorization.startsWith("Bearer ") and (authorization.length > 10)){
            return authorization.substring(7);
        }
        throw Exception("Token Expired! Please use /api/auth/login to login first");
    }

}