package com.bill.demo.entity

import jakarta.persistence.*

@Entity
@Table(name = "ACTORS")
class Actor (
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long = 0,
    var name: String,

    @ManyToMany(mappedBy = "actors")
    val movies: List<Movie>
)