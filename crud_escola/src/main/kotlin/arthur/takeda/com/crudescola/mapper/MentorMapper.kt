package arthur.takeda.com.crudescola.mapper

import arthur.takeda.com.crudescola.model.Mentor
import arthur.takeda.com.crudescola.dto.MentorDTO
import org.mapstruct.*

@Mapper(componentModel = "spring")
interface MentorMapper {

    fun toMentorDTO(mentor: Mentor): MentorDTO

    fun toMentor(mentorDTO: MentorDTO): Mentor

}