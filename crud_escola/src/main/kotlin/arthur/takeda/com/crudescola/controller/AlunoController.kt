package arthur.takeda.com.crudescola.controller

import arthur.takeda.com.crudescola.dto.AlunoDTO
import arthur.takeda.com.crudescola.model.Aluno
import arthur.takeda.com.crudescola.service.AlunoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/aluno")
class AlunoController(
    @Autowired
    val alunoService: AlunoService
) {

    @GetMapping
    fun findAll():ResponseEntity<List<AlunoDTO>>{
        return ResponseEntity.ok(alunoService.findAll());
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id:Long):ResponseEntity<AlunoDTO>{
        return ResponseEntity.ok(alunoService.findById(id));
    }

    @PostMapping("/post")
    fun save(@RequestBody aluno: AlunoDTO): ResponseEntity<Any> {
        return ResponseEntity.created(URI.create("/aluno/${alunoService.save(aluno)}")).build();
    }

    @PutMapping("/{id}")
    fun save(@RequestBody aluno: AlunoDTO, @PathVariable id: Long): ResponseEntity<Any>{
        return ResponseEntity.created(URI.create("/aluno/${alunoService.save(aluno, id)}")).build();
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        alunoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}