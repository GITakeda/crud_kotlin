package arthur.takeda.com.crudescola.service

import arthur.takeda.com.crudescola.dto.MentoriaDTO
import arthur.takeda.com.crudescola.dto.mapper.AlunoMapper
import arthur.takeda.com.crudescola.dto.mapper.MentoriaMapper
import arthur.takeda.com.crudescola.exception.NotFoundException
import arthur.takeda.com.crudescola.model.Mentoria
import arthur.takeda.com.crudescola.repository.MentoriaRepository
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
data class MentoriaService(
    val mentoriaRepository: MentoriaRepository,

    @Autowired
    val mapper: MentoriaMapper
) {

    fun findAll(): List<MentoriaDTO>{
        return mentoriaRepository.findAllByActive(true).map{mapper.toMentoriaDTO(it)}
    }

    fun findById(id:Long): MentoriaDTO{
        return mapper.toMentoriaDTO(mentoriaRepository.findByIdAndActive(id, true)?: throw NotFoundException("Mentoria não encontrada"));
    }

    fun save(mentoriaDTO: MentoriaDTO): Long{
        var mentoria: Mentoria = mapper.toMentoria(mentoriaDTO)

        mentoriaRepository.save(mentoria)

        return mentoria.id?:0;
    }

    fun save(mentoriaDTO: MentoriaDTO, id: Long): Long{
        var mentoria: Mentoria = mapper.toMentoria(mentoriaDTO)

        if(!mentoriaRepository.existsByIdAndActive(id, true)){
            throw NotFoundException("Mentoria não encontrada")
        }

        mentoria.id = id

        mentoriaRepository.save(mentoria)

        return id
    }

    @Transactional
    fun delete(id: Long){
        var mentoria: Mentoria = mentoriaRepository.findByIdAndActive(id, true)?:throw NotFoundException("Mentoria não encontrada")

        mentoria.active = false
    }


}