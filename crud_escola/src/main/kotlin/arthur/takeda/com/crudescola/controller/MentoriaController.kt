package arthur.takeda.com.crudescola.controller

import arthur.takeda.com.crudescola.dto.MentoriaDTO
import arthur.takeda.com.crudescola.model.Mentoria
import arthur.takeda.com.crudescola.service.MentoriaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/mentoria")
class MentoriaController (
    @Autowired
    val mentoriaService: MentoriaService
){

    @GetMapping
    fun findAll(): ResponseEntity<List<MentoriaDTO>> {
        return ResponseEntity.ok(mentoriaService.findAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long):ResponseEntity<MentoriaDTO>{
        return ResponseEntity.ok(mentoriaService.findById(id))
    }

    @PostMapping("/post")
    fun save(@RequestBody mentoriaDTO: MentoriaDTO): ResponseEntity<Any> {
        return ResponseEntity.created(URI("/mentoria/${mentoriaService.save(mentoriaDTO)}")).build()
    }

    @PutMapping("/{id}")
    fun save(@RequestBody mentoriaDTO: MentoriaDTO, @PathVariable id:Long): ResponseEntity<Any>{
        return ResponseEntity.created(URI("/mentoria/${mentoriaService.save(mentoriaDTO, id)}")).build()
    }

    @DeleteMapping("/{id}")
    fun save(@PathVariable id:Long): ResponseEntity<Any>{
        mentoriaService.delete(id)
        return ResponseEntity.noContent().build()
    }


}