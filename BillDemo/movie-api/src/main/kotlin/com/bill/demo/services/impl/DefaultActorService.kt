package com.bill.demo.services.impl

import com.bill.demo.entity.Actor
import com.bill.demo.exceptions.AppException
import com.bill.demo.repository.ActorRepository
import com.bill.demo.services.ActorService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DefaultActorService(
 val actorRepository: ActorRepository
): ActorService {

 override fun getAllActors(): List<Actor> {
  return actorRepository.findAll().toList()
 }

 override fun save(actor: Actor): Actor {
  return actorRepository.save(actor)
 }

 override fun findById(actorId: Long): Actor {
  return actorRepository.findById(actorId).orElseThrow {
     AppException("Actor not found", HttpStatus.NOT_FOUND)
  }
 }

 override fun deleteById(actorId: Long) {
  actorRepository.findById(actorId).orElseThrow {
     AppException("Actor not found", HttpStatus.NOT_FOUND)
  }
  actorRepository.deleteById(actorId)
 }

 override fun findActorByName(name: String): Actor? {
  return try {
   actorRepository.findActorByName(name)
  } catch (ex: EmptyResultDataAccessException){
   null;
  }
 }

}