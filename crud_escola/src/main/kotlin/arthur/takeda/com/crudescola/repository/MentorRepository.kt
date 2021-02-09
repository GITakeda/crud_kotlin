package arthur.takeda.com.crudescola.repository

import arthur.takeda.com.crudescola.model.Mentor
import org.springframework.data.jpa.repository.JpaRepository


interface MentorRepository: JpaRepository<Mentor, Long> {
    fun findAllByActive(active: Boolean):List<Mentor>;

    fun findByIdAndActive(id: Long, active: Boolean): Mentor?;

    fun existsByIdAndActive(id: Long, active: Boolean): Boolean;
}