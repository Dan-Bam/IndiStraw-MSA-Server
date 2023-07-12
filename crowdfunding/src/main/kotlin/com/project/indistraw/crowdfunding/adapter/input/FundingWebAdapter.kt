package com.project.indistraw.crowdfunding.adapter.input

import com.project.indistraw.crowdfunding.application.port.input.CreatePayInfoUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/funding")
class FundingWebAdapter(
    private val createPayInfoUseCase: CreatePayInfoUseCase
) {

    @PostMapping("receipt")
    fun createPayInfo(): ResponseEntity<Map<String, String>> =
        createPayInfoUseCase.execute()
            .let { ResponseEntity.ok(mapOf("receiptId" to it)) }

}