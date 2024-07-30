package com.agenda_service_back.prestador;

import com.agenda_service_back.prestador.Prestador;
import com.agenda_service_back.prestador.PrestadorDTO;
import com.agenda_service_back.prestador.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin (origins = "http://localhost:5173")
@RestController
@RequestMapping("/prestador")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    @GetMapping //retorna uma lista de prestadors
    public ResponseEntity<List<PrestadorDTO>> getAllPrestadors() {
        List<PrestadorDTO> prestadorsDTO = prestadorService.findAll();
        return ResponseEntity.ok(prestadorsDTO);
    }

    @GetMapping("/{id}") //localhost:8080/prestadors/1    pegar por id
    public ResponseEntity<PrestadorDTO> getPrestadorById(@PathVariable Long id) {
        PrestadorDTO prestadorDTO = prestadorService.findById(id);
        return ResponseEntity.ok(prestadorDTO);
    }

    @PostMapping //salvar uma categoria no banco de dados
    public ResponseEntity<PrestadorDTO> createPrestador(@RequestBody Prestador prestador) {
        System.out.println(prestador);
        PrestadorDTO createPrestadorDTO = prestadorService.create(prestador);
        return
                ResponseEntity.status(HttpStatus.CREATED).body(createPrestadorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestadorDTO> updatePrestador(@PathVariable Long id,
                                                        @RequestBody PrestadorDTO prestadorDTO) {
        PrestadorDTO updatePrestadorDTO = prestadorService.update(id, prestadorDTO);
        return ResponseEntity.ok(updatePrestadorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestador(@PathVariable Long id) {
        prestadorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PrestadorDTO>> getPrestadoresByServicoNome(@RequestParam String servicoNome) {
        System.out.println("servico_nome:" + servicoNome);
        if (servicoNome == null || servicoNome.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        try {
            List<PrestadorDTO> prestadores = prestadorService.findByServicoNome(servicoNome);
            System.out.println("lista de prestadores:" + prestadores);
            return ResponseEntity.ok(prestadores);
        } catch (Exception e) {
            // Log the exception (e.g., using SLF4J or another logging framework)
            // logger.error("Error while fetching prestadores by servicoNome: {}", servicoNome, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }

    }
}
