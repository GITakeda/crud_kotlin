package arthur.takeda.com.crudescola.mapper

import arthur.takeda.com.crudescola.dto.AlunoDTO
import arthur.takeda.com.crudescola.model.Aluno
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring")
interface AlunoMapper {

    fun toAlunoDTO(aluno: Aluno):  AlunoDTO;

    fun toAluno(alunoDTO: AlunoDTO): Aluno;

}