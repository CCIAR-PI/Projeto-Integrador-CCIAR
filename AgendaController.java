package cci.projetointegrador.controller;

import cci.projetointegrador.entity.Agenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AgendaController {
    @Autowired
    private AgendaRepository agendaRepository;
    @GetMapping("/{id}")
    public ResponseEntity<Agenda> findByIDPath (@PathVariable("id") final Long id) {
        final Agenda agenda = this.agendaRepository.findById(id).orElse(null);
        return ResponseEntity.ok(agenda);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.agendaRepository.findAll());
    }
    @PostMapping
    public ResponseEntity<?> cadastrar (@RequestBody final Agenda agenda) {
        try {
            this.agendaRepository.save(agenda);
            return ResponseEntity.ok("Agenda cadastrada com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Agenda agenda) {
        try {
            final Agenda agenda1 = this.agendaRepository.findById(id).orElse(null);

            if (agenda1 == null || agenda1.getId().equals(agenda.getId())) {
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.agendaRepository.save(agenda);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping ("delete/{id}")

    public void eletarAgenda (@PathVariable Long id)
    {
        agendaRepository.deleteById(id);
    }
}
