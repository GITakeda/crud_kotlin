package arthur.takeda.com.crudescola.service

import arthur.takeda.com.crudescola.model.Mentor
import arthur.takeda.com.crudescola.dto.MentorDTO
import arthur.takeda.com.crudescola.mapper.MentorMapper
import arthur.takeda.com.crudescola.exception.NotFoundException
import arthur.takeda.com.crudescola.repository.MentorRepository
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MentorService(
    private val mentorRepository: MentorRepository,

    private val mapper: MentorMapper
) {

    fun findAll(): List<MentorDTO> {
        return mentorRepository.findAllByActive(true).map{ mapper.toMentorDTO(it) };
    }

    fun findById(id: Long): MentorDTO {
        var mentor: Mentor = mentorRepository.findByIdAndActive(id, true)?:throw NotFoundException("Mentor não encontrado")

        return mapper.toMentorDTO(mentor)
    }

    fun save(mentorDTO: MentorDTO): Long{
        var mentor: Mentor = mapper.toMentor(mentorDTO)

        mentorRepository.save(mentor)

        return mentor.id?: 0
    }

    fun save(id: Long, mentorDTO: MentorDTO): Long{
        if(!mentorRepository.existsByIdAndActive(id, true)){
            throw NotFoundException("Mentor não encontrado")
        }

        var mentor: Mentor = mapper.toMentor(mentorDTO)

        mentor.id = id

        mentorRepository.save(mentor)

        return id
    }

    @Transactional
    fun delete(id: Long){
        var mentor: Mentor = mentorRepository.findByIdAndActive(id, true)?:throw NotFoundException("Mentor não encontrado")

        mentor.active = false
    }

}