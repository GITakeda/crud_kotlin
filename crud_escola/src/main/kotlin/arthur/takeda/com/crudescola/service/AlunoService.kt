package arthur.takeda.com.crudescola.service

import arthur.takeda.com.crudescola.dto.AlunoDTO
import arthur.takeda.com.crudescola.model.Aluno
import arthur.takeda.com.crudescola.repository.AlunoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import arthur.takeda.com.crudescola.dto.mapper.AlunoMapper
import arthur.takeda.com.crudescola.exception.NotFoundException
import org.mapstruct.factory.Mappers
import org.springframework.transaction.annotation.Transactional

@Service
class AlunoService(
    @Autowired
    val alunoRepository: AlunoRepository,

    val mapper: AlunoMapper = Mappers.getMapper(AlunoMapper::class.java)
) {

    fun findAll(): List<AlunoDTO> {
        return alunoRepository.findAllByActive(true).map { mapper.toAlunoDTO(it) };
    }

    fun findById(id: Long): AlunoDTO{
        var aluno: Aluno = alunoRepository.findByIdAndActive(id, true) ?: throw NotFoundException("Aluno não encontrado");

        return mapper.toAlunoDTO(aluno);
    }

    fun save(alunoDTO: AlunoDTO): Long{
        var aluno: Aluno = mapper.toAluno(alunoDTO);

        alunoRepository.save(aluno);

        return aluno.id?:0;
    }

    fun save(alunoDTO: AlunoDTO, id: Long): Long?{
        var aluno:Aluno = mapper.toAluno(alunoDTO);

        if(!alunoRepository.existsByIdAndActive(id, true)){
            throw NotFoundException("Aluno não encontrado");
        }

        aluno.id = id;

        alunoRepository.save(aluno);

        return aluno.id;
    }

    @Transactional
    fun delete(id: Long){
        var aluno: Aluno = alunoRepository.findByIdAndActive(id, true)?: throw NotFoundException("Aluno não encontrado");

        aluno.active = false;
    }
}