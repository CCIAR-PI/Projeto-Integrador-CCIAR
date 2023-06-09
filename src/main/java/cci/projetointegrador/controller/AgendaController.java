package cci.projetointegrador.controller;

import cci.projetointegrador.repository.AgendaRepository;
import cci.projetointegrador.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cci.projetointegrador.entity.Agenda;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller
public class AgendaController {
    
        @Autowired
        private AgendaRepository agendaRepository;
        @Autowired
        private AgendaService agendaService;
        @GetMapping("/{id}")
        public ResponseEntity<Agenda> findByIDPath (@PathVariable("id") final Long id) {
            final Agenda agenda = this.agendaRepository.findById(id).orElse(null);
            return ResponseEntity.ok(agenda);
        }

        @GetMapping("/lista")
        public ResponseEntity<?> ListaCompleta() {
            return Response
            Entity.ok(this.agendaRepository.findAll());
        }

        @PostMapping
        public ResponseEntity<?> cadastrar (@RequestBody final Agenda agenda) {
            try {
                agendaService.validaAgenda(agenda);
                return ResponseEntity.ok("Registro cadastrado com sucesso");
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
            }
        }

        @PutMapping
        public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Agenda agenda) {
            try {
                agendaService.validaAgenda(agenda);
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
        public void deletarAgenda (@PathVariable Long id)
        {
            agendaRepository.deleteById(id);
        }
    }


