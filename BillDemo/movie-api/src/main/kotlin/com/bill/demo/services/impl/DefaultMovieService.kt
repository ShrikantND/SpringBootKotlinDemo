package com.bill.demo.services.impl

import com.bill.demo.entity.Movie
import com.bill.demo.exceptions.AppException
import com.bill.demo.repository.MovieRepository
import com.bill.demo.services.MovieService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

@Service
class DefaultMovieService (val movieRepository: MovieRepository) : MovieService {

    override fun getAllMovies(): List<Movie> {
        return movieRepository.findAll().toList()
    }

    override fun save(movie: Movie): Movie {
        return movieRepository.save(movie)
    }

    override fun findById(movieId: Long): Movie {
        return movieRepository.findById(movieId).orElseThrow {
            AppException("Movie not found", HttpStatus.NOT_FOUND)
        }
    }

    override fun deleteById(movieId: Long) {
        movieRepository.findById(movieId).orElseThrow {
            AppException("Movie not found", HttpStatus.NOT_FOUND)
        }
        movieRepository.deleteById(movieId)
    }

    override fun findMovieByTitleAndDate(title: String, releaseDate: LocalDate): Movie? {
        return try {
            return movieRepository.findMovieByTitleAndDate(title, releaseDate)
        } catch (ex: EmptyResultDataAccessException){
            null;
        }
    }

}