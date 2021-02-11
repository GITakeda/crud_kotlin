package arthur.takeda.com.crudescola.repository

import arthur.takeda.com.crudescola.model.Mentoria
import org.springframework.data.jpa.repository.JpaRepository

interface MentoriaRepository: JpaRepository<Mentoria, Long> {

    fun findAllByActive(active: Boolean):List<Mentoria>;

    fun findByIdAndActive(id: Long, active: Boolean): Mentoria?;

    fun existsByIdAndActive(id: Long, active: Boolean): Boolean;

}