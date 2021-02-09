package arthur.takeda.com.crudescola.dto.mapper

import arthur.takeda.com.crudescola.dto.MentoriaDTO
import arthur.takeda.com.crudescola.dto.mapper.decorator.MentoriaDecorator
import arthur.takeda.com.crudescola.model.Mentoria
import org.mapstruct.DecoratedWith
import org.mapstruct.Mapper


@Mapper
@DecoratedWith(MentoriaDecorator::class)
interface MentoriaMapper {

    fun toMentoriaDTO(mentoria: Mentoria): MentoriaDTO

    fun toMentoria(mentoriaDTO: MentoriaDTO): Mentoria

}