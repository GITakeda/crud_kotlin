package arthur.takeda.com.crudescola.repository

import arthur.takeda.com.crudescola.model.Aluno
import org.springframework.data.jpa.repository.JpaRepository


interface AlunoRepository:JpaRepository<Aluno, Long> {

    fun findAllByActive(active: Boolean):List<Aluno>;

    fun findByIdAndActive(id: Long, active: Boolean):Aluno?;

    fun existsByIdAndActive(id: Long, active: Boolean): Boolean;

}