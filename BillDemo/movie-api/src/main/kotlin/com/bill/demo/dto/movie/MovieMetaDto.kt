package com.bill.demo.dto.movie

import com.bill.demo.entity.Movie
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class MovieMetaDto(
    val id: Long,
    @field:NotEmpty(message = "Movie title can not be empty.")
    @field:Size(min=1, max=30, message="Move title must have at least 1 character and should not exceed by 30 characters")
    val title: String,
    val releaseDate: LocalDate
)

fun Movie.toMovieMetaDto() = MovieMetaDto(
    id = id,
    title = title,
    releaseDate = releaseDate
)