package me.dio.creditapplicationsystem.service.impl

import me.dio.creditapplicationsystem.entity.Credit
import me.dio.creditapplicationsystem.repository.CreditRepository
import me.dio.creditapplicationsystem.service.ICreditService
import org.hibernate.validator.constraints.UUID
import org.springframework.stereotype.Service

@Service
class CreditService(
    private val creditRepostitory: CreditRepository,
    private val customerService: CustomerService
): ICreditService {

    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepostitory.save(credit)
    }

    override fun findAllByCudtomer(customerId: Long): List<Credit> {
        return this.creditRepostitory.findAllByCustomerId(customerId)
    }

    override fun findBycreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = (this.creditRepostitory.findByCreditCode(creditCode)
           ?: throw RuntimeException("Creditcode $creditCode not found"))
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact Admin")
    }

}