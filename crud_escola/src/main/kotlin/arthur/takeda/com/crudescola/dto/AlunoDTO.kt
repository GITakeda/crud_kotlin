package arthur.takeda.com.crudescola.dto

import arthur.takeda.com.crudescola.model.Aluno

data class AlunoDTO(
    var id: Long? = null,
    var nome: String? = null,
    var idade: Int? = null
)