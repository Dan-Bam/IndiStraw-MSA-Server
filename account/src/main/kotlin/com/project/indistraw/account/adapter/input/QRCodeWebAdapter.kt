package com.project.indistraw.account.adapter.input

import com.project.indistraw.account.application.port.input.CheckQRCodeUUIDUseCase
import com.project.indistraw.account.application.port.input.CheckSsePingUseCase
import com.project.indistraw.account.application.port.input.ConnectSseUseCase
import com.project.indistraw.account.application.port.input.CreateQRCodeUUIDUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.util.*

@RestController
@RequestMapping("api/v1/qr-code")
class QRCodeWebAdapter(
    private val createQRCodeUUIDUseCase: CreateQRCodeUUIDUseCase,
    private val connectSseUseCase: ConnectSseUseCase,
    private val checkQRCodeUUIDUseCase: CheckQRCodeUUIDUseCase,
    private val checkSsePingUseCase: CheckSsePingUseCase
) {

    @PostMapping
    fun createUUID(): ResponseEntity<Map<String, UUID>> =
        createQRCodeUUIDUseCase.execute()
            .let { ResponseEntity.ok(mapOf("uuid" to it)) }

    @GetMapping(value = ["/connect/{uuid}"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun sseConnect(@PathVariable uuid: UUID): ResponseEntity<SseEmitter> =
        connectSseUseCase.execute(uuid)
            .let { ResponseEntity.ok(it) }

    @RequestMapping(value = ["/check/{uuid}"], method = [RequestMethod.HEAD])
    fun checkUUID(@PathVariable uuid: UUID): ResponseEntity<Void> =
        checkQRCodeUUIDUseCase.execute(uuid)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @GetMapping("ping/{uuid}")
    fun checkPing(@PathVariable uuid: UUID): ResponseEntity<Void> =
        checkSsePingUseCase.execute(uuid)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}