package com.bill.demo.controllers

import com.bill.demo.dto.movie.*
import com.bill.demo.exceptions.AppException
import com.bill.demo.services.impl.DefaultActorService
import com.bill.demo.services.impl.DefaultMovieService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/movies")
class MovieController(val movieService: DefaultMovieService, val actorService: DefaultActorService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_Read')")
    fun getAllMovies(): ResponseEntity<List<MovieDto>> {
        val movies = movieService.getAllMovies()
        return ResponseEntity.ok(movies.map { it.toMovieDto() })
    }

    @GetMapping("/{movieId}")
    @PreAuthorize("hasAuthority('ROLE_Read')")
    fun getMovie(
        @PathVariable movieId: Long
    ) : ResponseEntity<MovieDto> {
        val movie = movieService.findById(movieId)
        return ResponseEntity.ok(movie.toMovieDto())
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_Write')")
    @Transactional
    fun addMovie(
        @Valid @RequestBody createMovieDto: CreateMovieDto
    ): ResponseEntity<MovieDto> {
        var movieByNameAndReleaseDate = movieService.findMovieByTitleAndDate(createMovieDto.title, createMovieDto.releaseDate)
        if (movieByNameAndReleaseDate!=null) {
            throw AppException("Movie with same name and release date already exists", HttpStatus.FORBIDDEN)
        }
        createMovieDto.actors.map {
            actorDto ->  actorService.findById(actorDto.id)
        }
        val movie = movieService.save(createMovieDto.toMovie())
        return ResponseEntity.ok(movie.toMovieDto())
    }

    @DeleteMapping("/{movieId}")
    @PreAuthorize("hasAuthority('ROLE_Write')")
    fun deleteMovie(
        @PathVariable movieId: Long
    ) {
        movieService.findById(movieId)
        movieService.deleteById(movieId)
    }

    @PatchMapping("/{movieId}")
    @PreAuthorize("hasAuthority('ROLE_Write')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun updateActor(
        @PathVariable movieId: Long,
        @Valid @RequestBody updateMovieDto: UpdateMovieDto
    ): ResponseEntity<MovieDto> {
        var movie = movieService.findById(movieId)
        var movieByNameAndReleaseDate = movieService.findMovieByTitleAndDate(updateMovieDto.title, updateMovieDto.releaseDate)
        if (movieByNameAndReleaseDate!=null) {
            throw AppException("Movie with same name and release date already exists", HttpStatus.FORBIDDEN)
        }
        movie.title = updateMovieDto.title
        movie.releaseDate = updateMovieDto.releaseDate
        movie = movieService.save(movie)
        return ResponseEntity.ok(movie.toMovieDto())
    }

}