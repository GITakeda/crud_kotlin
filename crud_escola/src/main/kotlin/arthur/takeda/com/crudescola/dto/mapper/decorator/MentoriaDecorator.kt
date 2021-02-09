package arthur.takeda.com.crudescola.dto.mapper.decorator

import arthur.takeda.com.crudescola.dto.AlunoDTO
import arthur.takeda.com.crudescola.dto.MentorDTO
import arthur.takeda.com.crudescola.dto.MentoriaDTO
import arthur.takeda.com.crudescola.dto.mapper.AlunoMapper
import arthur.takeda.com.crudescola.dto.mapper.MentorMapper
import arthur.takeda.com.crudescola.dto.mapper.MentoriaMapper
import arthur.takeda.com.crudescola.model.Aluno
import arthur.takeda.com.crudescola.model.Mentor
import arthur.takeda.com.crudescola.model.Mentoria
import arthur.takeda.com.crudescola.service.AlunoService
import arthur.takeda.com.crudescola.service.MentorService
import org.aspectj.weaver.reflect.ReflectionBasedReferenceTypeDelegateFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

class MentoriaDecorator(
    @Autowired
    val mentorService: MentorService,

    @Autowired
    val alunoService: AlunoService,

    @Autowired
    val mentorMapper: MentorMapper,

    @Autowired
    val alunoMapper: AlunoMapper,

    val delegate: MentoriaMapper
): MentoriaMapper by delegate {
    override fun toMentoriaDTO(mentoria: Mentoria): MentoriaDTO {
        return delegate.toMentoriaDTO(mentoria)
    }

    override fun toMentoria(mentoriaDTO: MentoriaDTO): Mentoria{
        var mentoria: Mentoria = delegate.toMentoria(mentoriaDTO)

        var mentor: Mentor = mentorMapper.toMentor(mentorService.findById(mentoriaDTO.mentorId?: 0))
        var aluno: Aluno = alunoMapper.toAluno(alunoService.findById(mentoriaDTO.alunoId?: 0))

        mentoria.aluno = aluno
        mentoria.mentor = mentor

        return mentoria;
    }
}