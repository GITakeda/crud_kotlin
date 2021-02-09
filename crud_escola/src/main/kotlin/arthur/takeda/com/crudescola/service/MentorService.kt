package arthur.takeda.com.crudescola.service

import arthur.takeda.com.crudescola.model.Mentor
import arthur.takeda.com.crudescola.dto.MentorDTO
import arthur.takeda.com.crudescola.dto.mapper.MentorMapper
import arthur.takeda.com.crudescola.repository.MentorRepository
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MentorService(
    @Autowired
    val mentorRepository: MentorRepository,

    val mapper: MentorMapper = Mappers.getMapper(MentorMapper::class.java)
) {

    fun findAll(): List<MentorDTO> {
        return mentorRepository.findAllByActive(true).map{ mapper.toMentorDTO(it) };
    }

    fun findById(id: Long): MentorDTO {
        var mentor: Mentor = mentorRepository.findByIdAndActive(id, true)?:throw Exception("Mentor não encontrado")

        return mapper.toMentorDTO(mentor)
    }

    fun save(mentorDTO: MentorDTO): Long{
        var mentor: Mentor = mapper.toMentor(mentorDTO)

        mentorRepository.save(mentor)

        return mentor.id?: 0
    }

    fun save(id: Long, mentorDTO: MentorDTO): Long{
        if(!mentorRepository.existsByIdAndActive(id, true)){
            throw Exception("Mentor não encontrado")
        }

        var mentor: Mentor = mapper.toMentor(mentorDTO)

        mentor.id = id

        mentorRepository.save(mentor)

        return id
    }

    @Transactional
    fun delete(id: Long){
        var mentor: Mentor = mentorRepository.findByIdAndActive(id, true)?:throw Exception("Mentor não encontrado")

        mentor.active = false
    }

}