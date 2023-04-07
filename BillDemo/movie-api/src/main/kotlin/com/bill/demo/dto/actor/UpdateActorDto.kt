package com.bill.demo.dto.actor

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class UpdateActorDto (
    @field:NotEmpty(message = "Actor name can not be empty.")
    @field:Size(min=1, max=30, message="Actor name must have at least 1 character and should not exceed by 30 characters")
    val name: String
)
