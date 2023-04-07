package com.bill.demo.services

import com.bill.demo.entity.Movie
import java.time.LocalDate

interface MovieService {

    fun getAllMovies(): List<Movie>
    fun save(toEntity: Movie): Any
    fun findById(movieId: Long): Movie
    fun deleteById(movieId: Long)
    fun findMovieByTitleAndDate(title: String, releaseDate: LocalDate): Movie?

}
