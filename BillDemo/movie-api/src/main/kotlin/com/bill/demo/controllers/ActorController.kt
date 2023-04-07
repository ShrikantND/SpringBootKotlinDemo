package com.bill.demo.controllers

import com.bill.demo.dto.actor.*
import com.bill.demo.exceptions.AppException
import com.bill.demo.services.impl.DefaultActorService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/actors")
class ActorController(val actorService: DefaultActorService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_Write')")
    fun getAllActors(): ResponseEntity<List<ActorDto>> {
        val actors = actorService.getAllActors()
        return ResponseEntity.ok(actors.map { it.toActorDto() })
    }

    @GetMapping("/{actorId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_Read')")
    fun getActor(
        @PathVariable actorId: Long
    ) : ResponseEntity<ActorMoviesDto> {
        val actor = actorService.findById(actorId)
        return ResponseEntity.ok(actor.toActorMoviesDto())
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_Read')")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    fun addActor(
        @Valid @RequestBody createActorDto: CreateActorDto
    ): ResponseEntity<ActorDto> {
        var actorByName = actorService.findActorByName(createActorDto.name)
        if (actorByName!=null) {
            throw AppException("Actor with this name already exists", HttpStatus.FORBIDDEN)
        }
        val actor = actorService.save(createActorDto.toActor())
        return ResponseEntity.ok(actor.toActorDto())
    }

    @DeleteMapping("/{actorId}")
    @PreAuthorize("hasAuthority('ROLE_Write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteActor(
        @PathVariable actorId: Long
    ) {
        var actor = actorService.findById(actorId)
        if(actor.movies.isNotEmpty()){
            throw AppException("Actor is associated with movie and can not be deleted", HttpStatus.FORBIDDEN)
        }
        actorService.deleteById(actorId)
    }

    @PatchMapping("/{actorId}")
    @PreAuthorize("hasAuthority('ROLE_Write')")
    @Transactional
    fun updateActor(
        @PathVariable actorId: Long,
        @Valid @RequestBody updateActorDto: UpdateActorDto
    ): ResponseEntity<ActorDto> {
        var actorByName = actorService.findActorByName(updateActorDto.name)
        if (actorByName!=null) {
            throw AppException("Actor with this name already exists", HttpStatus.FORBIDDEN)
        }
        var actor = actorService.findById(actorId)
        actor.name = updateActorDto.name
        actor = actorService.save(actor)
        return ResponseEntity.ok(actor.toActorDto())
    }

}