package com.bill.demo.dto.movie

import com.bill.demo.dto.actor.ActorDto
import com.bill.demo.dto.actor.toActor
import com.bill.demo.entity.Movie
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class CreateMovieDto (
    @field:NotEmpty(message = "Movie title can not be empty.")
    @field:Size(min=1, max=30, message="Move title must have at least 1 character and should not exceed by 30 characters")
    val title: String,
    val releaseDate: LocalDate,
    @field:NotEmpty(message = "Movie must have actor(s)")
    @field:Size(min=1, max=30, message="Movie must have at least 1 or max 30 actors")
    val actors: List<ActorDto>
)

fun CreateMovieDto.toMovie() = Movie(
    title = title,
    releaseDate = releaseDate,
    actors = actors.map { it.toActor() }
)