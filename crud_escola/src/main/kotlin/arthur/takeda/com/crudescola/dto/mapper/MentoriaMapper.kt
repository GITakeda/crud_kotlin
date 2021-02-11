package arthur.takeda.com.crudescola.dto.mapper

import arthur.takeda.com.crudescola.dto.AlunoDTO
import arthur.takeda.com.crudescola.dto.MentoriaDTO
import arthur.takeda.com.crudescola.model.Aluno
import arthur.takeda.com.crudescola.model.Mentor
import arthur.takeda.com.crudescola.model.Mentoria
import arthur.takeda.com.crudescola.repository.AlunoRepository
import arthur.takeda.com.crudescola.service.AlunoService
import arthur.takeda.com.crudescola.service.MentorService
import org.mapstruct.*
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping


@Mapper(componentModel = "spring")
abstract class MentoriaMapper{
    @Autowired
    lateinit var alunoService: AlunoService

    @Autowired
    lateinit var mentorService: MentorService

    val alunoMapper: AlunoMapper = Mappers.getMapper(AlunoMapper::class.java)
    val mentorMapper: MentorMapper = Mappers.getMapper(MentorMapper::class.java)

    @Mappings(
        Mapping(target = "alunoId", source = "aluno.id"),
        Mapping(target = "mentorId", source = "mentor.id")
    )
    abstract fun toMentoriaDTO(mentoria: Mentoria): MentoriaDTO

    @Mappings(
        Mapping(target = "aluno", source = "alunoId", qualifiedByName = ["alunoIdToAluno"]),
        Mapping(target = "mentor", source = "mentorId", qualifiedByName = ["mentorIdToMentor"])
    )
    abstract fun toMentoria(mentoriaDTO: MentoriaDTO): Mentoria

    @Named("alunoIdToAluno")
    fun alunoIdToAluno(alunoId:Long): Aluno{
        var aluno: Aluno = alunoMapper.toAluno(alunoService.findById(alunoId))

        return aluno
    }

    @Named("mentorIdToMentor")
    fun mentorIdToMentor(mentorId: Long): Mentor{
        var mentor: Mentor = mentorMapper.toMentor(mentorService.findById(mentorId))

        return mentor
    }
}