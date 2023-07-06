package com.project.indistraw.account.adapter.input

import com.project.indistraw.account.application.port.input.CheckQRCodeUUIDUseCase
import com.project.indistraw.account.application.port.input.CreateQRCodeUUIDUseCase
import com.project.indistraw.account.application.service.CheckSSEPingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/v1/qr-code")
class QRCodeWebAdapter(
    private val createQRCodeUUIDUseCase: CreateQRCodeUUIDUseCase,
    private val checkQRCodeUUIDUseCase: CheckQRCodeUUIDUseCase,
    private val checkSSEPingService: CheckSSEPingService
) {

    @PostMapping
    fun createUUID(): ResponseEntity<Map<String, UUID>> =
        createQRCodeUUIDUseCase.execute()
            .let { ResponseEntity.ok(mapOf("uuid" to it)) }

    @RequestMapping(value = ["/check/uuid/{uuid}"], method = [RequestMethod.HEAD])
    fun checkUUID(@PathVariable uuid: UUID): ResponseEntity<Void> =
        checkQRCodeUUIDUseCase.execute(uuid)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @GetMapping("ping/{uuid}")
    fun checkPing(@PathVariable uuid: UUID): ResponseEntity<Void> =
        checkSSEPingService.execute(uuid)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}