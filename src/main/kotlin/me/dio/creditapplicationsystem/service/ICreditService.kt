package me.dio.creditapplicationsystem.service

import me.dio.creditapplicationsystem.entity.Credit
import org.hibernate.validator.constraints.UUID

interface ICreditService {
    fun save(credit: Credit): Credit
    fun findAllByCustomer(customerId: Long): List<Credit>
    fun findBycreditCode(customerId: Long,creditCode: UUID): Credit
}