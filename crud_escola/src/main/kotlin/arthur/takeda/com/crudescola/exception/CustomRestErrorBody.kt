package arthur.takeda.com.crudescola.exception

import org.springframework.http.HttpStatus

data class CustomRestErrorBody (
    val message: String,
    val httpStatus: HttpStatus
)