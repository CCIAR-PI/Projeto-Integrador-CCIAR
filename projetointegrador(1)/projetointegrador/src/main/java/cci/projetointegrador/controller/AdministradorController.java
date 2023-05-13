package cci.projetointegrador.controller;

import cci.projetointegrador.entity.Administrador;
import cci.projetointegrador.repository.AdministradorRepository;
import cci.projetointegrador.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/administrador")
public class AdministradorController {
    @Autowired
    private AdministradorRepository administradorRep;

    @Autowired
    private AdministradorService administradorService;

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> findByIDPath(@PathVariable("id") final Long id) {
        final Administrador administrador = this.administradorRep.findById(id).orElse(null);
        return ResponseEntity.ok(administrador);
    }


    @GetMapping("/lista")
    public ResponseEntity<?> ListaCompleta() {
        return ResponseEntity.ok(this.administradorRep.findAll());

    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final Administrador administrador) {
        try {
            administradorService.validaAdm(administrador);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Administrador administrador) {
        try {
            administradorService.validaAdm(administrador);
            final Administrador administrador1 = this.administradorRep.findById(id).orElse(null);

            if (administrador1 == null || administrador1.getId().equals(administrador.getId())) {
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.administradorRep.save(administrador);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")

    public void deletarAdministrador(@PathVariable Long id) {
        administradorRep.deleteById(id);
    }

}



