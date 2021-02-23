package arthur.takeda.com.crudescola.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class AlunoDTO(
    var id: Long? = null,
    @field:NotNull @field:NotBlank @field:NotEmpty
    var nome: String,
    @field:NotNull
    val idade: Int
)