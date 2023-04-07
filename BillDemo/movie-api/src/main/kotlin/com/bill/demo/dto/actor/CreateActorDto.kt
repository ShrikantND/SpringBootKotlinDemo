package com.bill.demo.dto.actor

import com.bill.demo.entity.Actor
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class CreateActorDto(
    @field:NotEmpty(message = "Actor name can not be empty.")
    @field:Size(min=1, max=30, message="Actor name must have at least 1 character and should not exceed by 30 characters")
    val name: String
)

fun CreateActorDto.toActor() = Actor(
    name = name,
    movies = emptyList()
)


