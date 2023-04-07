package com.bill.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class ActorRepositoryTest {
//    @Autowired var entityManager: TestEntityManager
//    @Autowired var actorRepository: ActorRepository
//
//    @Test
//    fun `When findByIdOrNull then return Article`() {
//        var johnDoe = Actor("johnDoe")
//        entityManager. .persist(johnDoe)
//        val article = Article("Lorem", "Lorem", "dolor sit amet", johnDoe)
//        entityManager.persist(article)
//        entityManager.flush()
//        val found = articleRepository.findByIdOrNull(article.id!!)
//        assertThat(found).isEqualTo(article)
//    }
}
