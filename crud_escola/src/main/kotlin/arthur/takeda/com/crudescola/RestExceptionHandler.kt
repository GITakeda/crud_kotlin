package arthur.takeda.com.crudescola

import arthur.takeda.com.crudescola.exception.CustomRestErrorBody
import arthur.takeda.com.crudescola.exception.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    fun notFoundExceptionHandler(exception: NotFoundException): ResponseEntity<CustomRestErrorBody> {
        return ResponseEntity(CustomRestErrorBody(exception.message?:"", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND)
    }
}