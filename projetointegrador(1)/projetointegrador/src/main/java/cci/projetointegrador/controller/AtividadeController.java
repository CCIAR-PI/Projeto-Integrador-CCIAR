package cci.projetointegrador.controller;


import cci.projetointegrador.entity.Atividade;
import cci.projetointegrador.repository.AtividadeRepository;
import cci.projetointegrador.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/atividade")

public class AtividadeController {
    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping("/{id}")
    public ResponseEntity<Atividade> findByIDPath(@PathVariable("id") final Long id) {
        final Atividade atividade = this.atividadeRepository.findById(id).orElse(null);
        return ResponseEntity.ok(atividade);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.atividadeRepository.findAll());

    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Atividade atividade) {
        try {
            atividadeService.validaAtividade(atividade);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Atividade atividade) {
        try {
            atividadeService.validaAtividade(atividade);

            final Atividade atividade1 = this.atividadeRepository.findById(id).orElse(null);
            if (atividade1 == null || atividade1.getId().equals(atividade.getId())) {
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.atividadeRepository.save(atividade);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")

    public void deletarAtividade(@PathVariable Long id) {
        atividadeRepository.deleteById(id);
    }

}



