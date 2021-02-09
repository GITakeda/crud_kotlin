package arthur.takeda.com.crudescola.dto.mapper

import arthur.takeda.com.crudescola.dto.AlunoDTO
import arthur.takeda.com.crudescola.model.Aluno
import org.mapstruct.Mapper

@Mapper
interface AlunoMapper {

    fun toAlunoDTO(aluno: Aluno):  AlunoDTO;

    fun toAluno(alunoDTO: AlunoDTO): Aluno;

}