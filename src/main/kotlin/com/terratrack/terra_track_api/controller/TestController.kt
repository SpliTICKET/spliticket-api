package com.terratrack.terra_track_api.controller

import com.terratrack.terra_track_api.entity.Enclosure
import com.terratrack.terra_track_api.service.EnclosureService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/api/test")
class TestController(
    private val enclosureService: EnclosureService
) {

    @GetMapping
    fun test(): Enclosure {
        val enclosure = Enclosure(
            name = "TestEnclosure",
            length = 80,
            height = 160,
            width = 60,
            ownerId = UUID.fromString("5070b79a-01a0-4107-bbe4-d77739159264")
        )

        return enclosureService.save(enclosure)
    }
}