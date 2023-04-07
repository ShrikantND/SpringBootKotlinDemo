package com.bill.demo.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "MOVIES")
data class Movie (
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
    var title: String,
    var releaseDate: LocalDate = LocalDate.now(),
    @ManyToMany
    @JoinTable(
        name= "MOVIE_AND_ACTORS",
        joinColumns = arrayOf(JoinColumn(name ="movie_id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "actor_id"))
    )
    val actors: List<Actor>
)