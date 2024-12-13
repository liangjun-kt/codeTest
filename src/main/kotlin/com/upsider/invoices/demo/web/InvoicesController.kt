package com.upsider.invoices.demo.web

import com.upsider.invoices.demo.model.request.AddInvoicesDTO
import com.upsider.invoices.demo.service.AuthService
import com.upsider.invoices.demo.service.InvoicesService
import jakarta.validation.Valid
import org.jetbrains.annotations.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api")
class InvoicesController(val authService: AuthService,
                         val invoicesService: InvoicesService) {
    @PostMapping("/invoices")
    fun addNewInvoices(@RequestHeader(value="Authorization") authorization:String,
                       @Valid @RequestBody addInvoicesDTO: AddInvoicesDTO, ):ResponseEntity<Any>{
        val accessToken = this.getTokenFromHeader(authorization)
        authService.validTokenAction(accessToken, addInvoicesDTO.userId.toString())
        invoicesService.insertInvoices(addInvoicesDTO)
        return ResponseEntity.ok("")
    }

    @GetMapping("/invoices")
    fun listAllInvoices(@RequestHeader(value="Authorization") authorization:String,
                        @NotNull @RequestParam from: String,
                        @NotNull @RequestParam to: String):ResponseEntity<Any>{

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
            start, end)
        return ResponseEntity.ok(list)
    }

    private fun getTokenFromHeader(authorization: String): String{
        if(authorization.startsWith("Bearer ") and (authorization.length > 10)){
            return authorization.substring(7);
        }
        throw Exception("Token Expired! Please use /api/auth/login to login first");
    }

}