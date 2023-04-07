package com.bill.demo.services

import com.bill.demo.entity.Actor

interface ActorService {

    fun getAllActors(): List<Actor>
    fun save(actor: Actor): Actor
    fun findById(actorId: Long): Actor
    fun deleteById(actorId: Long)
    fun findActorByName(name: String): Actor?

}