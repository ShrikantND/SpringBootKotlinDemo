package com.bill.demo.repository

import com.bill.demo.entity.Actor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ActorRepository: JpaRepository<Actor, Long> {


    @Query(
        "SELECT a FROM Actor a WHERE a.name = ?1"
    )
    fun findActorByName(name: String): Actor
}