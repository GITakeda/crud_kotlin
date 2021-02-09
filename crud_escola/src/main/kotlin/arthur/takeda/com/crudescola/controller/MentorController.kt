package arthur.takeda.com.crudescola.controller

import arthur.takeda.com.crudescola.dto.MentorDTO
import arthur.takeda.com.crudescola.model.Mentor
import arthur.takeda.com.crudescola.service.MentorService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/mentor")
class MentorController(
    @Autowired
    val mentorService: MentorService
) {

    @GetMapping
    fun findAll(): ResponseEntity<List<MentorDTO>> {
        return ResponseEntity.ok(mentorService.findAll())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<MentorDTO>{
        return ResponseEntity.ok(mentorService.findById(id))
    }

    @PostMapping("/post")
    fun save(@RequestBody mentorDTO: MentorDTO): ResponseEntity<Any>{
        return ResponseEntity.created(URI("/mentor/${mentorService.save(mentorDTO)}")).build()
    }

    @PutMapping("/{id}")
    fun save(@PathVariable id:Long, @RequestBody mentorDTO: MentorDTO): ResponseEntity<Any>{
        return ResponseEntity.created(URI("/mentor/${mentorService.save(id, mentorDTO)}")).build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long):ResponseEntity<Any>{
        mentorService.delete(id)

        return ResponseEntity.noContent().build()
    }

}