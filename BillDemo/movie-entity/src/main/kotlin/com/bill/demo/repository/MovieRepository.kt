package com.bill.demo.repository

import com.bill.demo.entity.Movie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface MovieRepository: JpaRepository<Movie, Long> {

    @Query(
        "SELECT m FROM Movie m WHERE m.title = ?1 and m.releaseDate = ?2"
    )
    fun findMovieByTitleAndDate(title: String, releaseDate: LocalDate): Movie
}