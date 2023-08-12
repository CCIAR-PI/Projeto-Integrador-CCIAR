package projetointegrador.cciar.projetointegradorcciar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projetointegrador.cciar.projetointegradorcciar.entity.Atividade;
import projetointegrador.cciar.projetointegradorcciar.repository.AtividadeRepository;
import projetointegrador.cciar.projetointegradorcciar.service.AtividadeService;

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
            return ResponseEntity.ok("Atividade cadastrada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final Atividade atividade) {
        try {
            atividadeService.validaAtividade(atividade);

            final Atividade atividade1 = this.atividadeRepository.findById(id).orElse(null);
            if (atividade1 == null || !atividade1.getId().equals(atividade.getId())) {
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            return ResponseEntity.ok("Atividade atualizada com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletaAtividade (@PathVariable ("id") final Long id){
        try {
            this.atividadeService.deletarAtividade(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
