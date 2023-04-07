package com.bill.demo.service.impl

import com.bill.demo.entity.Actor
import com.bill.demo.repository.ActorRepository
import com.bill.demo.services.ActorService
import com.bill.demo.services.impl.DefaultActorService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*


class DefaultActorServiceImplTest {
    private val actorRepository : ActorRepository = mockk();
    var actorService: ActorService = DefaultActorService(actorRepository)

    @Test
    fun whenGetActor_thenReturnActor() {
        val actor: Actor = Actor (1,"", emptyList())
        //given
        every { actorRepository.findById(1) } returns Optional.of(actor);
        //when
        val result = actorService.findById(1);
        //then
        verify(exactly = 1) { actorRepository.findById(1) };
        assertEquals(actor, result)
    }
}